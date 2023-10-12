package academy.prog.chatserversprint.service;

import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final MessageRepository messageRepository;

    public UserService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
}