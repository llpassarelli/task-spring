package br.com.llpassarelli.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.llpassarelli.dao.TaskDao;
import br.com.llpassarelli.entity.Task;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class TaskController {
	
    @Autowired
    private TaskDao TaskDao;
    private long lastRec;
 
    @RequestMapping("/")
    public ModelAndView handleRequest() throws Exception {
        List<Task> listTasks = TaskDao.list();
        ModelAndView model = new ModelAndView("task");
        model.addObject("taskList", listTasks);
        return model;
    }
    
    @RequestMapping("/manager")
    public ModelAndView manager() throws Exception {
        List<Task> listTasks = TaskDao.list();
        ModelAndView model = new ModelAndView("manager");
        if(!listTasks.isEmpty()){
        	Task taskNext = listTasks.get(0);
        	Long taskNextId = taskNext.getId();
        	model.addObject("taskNextId",taskNextId);
        	model.addObject("taskList", listTasks);
        }
        return model;
    }
    
    @RequestMapping("/call")
    public ModelAndView call() throws Exception {
    	System.out.println("last: "+this.lastRec);
    	Task last = TaskDao.get((int) this.lastRec);
        ModelAndView model = new ModelAndView("call");
        
    	Long taskNextId = last.getId();
    	String priority = last.getPrioridade();
    	String prefix = "N";
    	if(priority.equals("prioritario")){
    		prefix = "P";
    	}
    	String msg = "PRÓXIMA SENHA "+ prefix + taskNextId;
    	model.addObject("css","info");
    	model.addObject("msg",msg);
    
        return model;
    }
     
    @RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
    public ModelAndView newTask(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
    	String prioridade = "normal";
    	String prefix = "N";
    	if(id == 0){
    		prioridade="prioritario";
    		prefix = "P";
    	}
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Date date = new Date();
    	String dateStr = dateFormat.format(date);
    	Task task = new Task(prioridade,"aberta", dateStr);
    	long idSaved = TaskDao.saveOrUpdate(task);  
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Senha " + prefix + idSaved +" criada com sucesso!");
        return new ModelAndView("redirect:/");      
    }
     
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editTask(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        Task task = TaskDao.get(id);
    	String priority = task.getPrioridade();
    	String prefix = "N";
    	if(priority.equals("prioritario")){
    		prefix = "P";
    	}
    	task.setStatus("fechado");
    	TaskDao.saveOrUpdate(task);
    	this.lastRec = id;
    	redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Senha " + prefix + id +" alterada com sucesso!");
        return new ModelAndView("redirect:/manager");      
    }
     
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteTask() {
        TaskDao.delete();
        return new ModelAndView("redirect:/");     
    }
     
}