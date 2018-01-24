package com.crud.tasks.scheduler;


import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private static final String TASK = " task";
    private static final String TASKS = " tasks";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron ="0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        String word = size>1 ? TASKS : TASK;
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),adminConfig.getAdminMail(),SUBJECT,"Currently in database yo got: "+size+ word)

        );

    }
}
