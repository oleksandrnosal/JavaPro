package academy.prog.chatserversprint.service;

import academy.prog.chatserversprint.model.FileDTO;
import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.model.MessageDTO;
import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void add(MessageDTO messageDTO) {
        var message = Message.fromDTO(messageDTO);
        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> get(long id) {
        var messages = messageRepository.findNew(id);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }

    public MessageDTO getByMessageId(Long messageId) {
        Message message = messageRepository.findById(messageId).get();
        return message.toDTO();
    }
@Transactional(readOnly = true)
    public List<String> getAllActiveUsers() {
        var messages = messageRepository.getAllActiveUsers();
        var result = new ArrayList<String>();

        messages.forEach(message -> result.add(message.getFrom()));
        return result;
    }

    @Transactional (readOnly = true)
    public List<MessageDTO> getPrivateMessage(String to){
        var messages = messageRepository.findPrivateMessage(to);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    public List<FileDTO> getFileInMessage(Long id){
        var messageList = messageRepository.findById(id);
        Message message = messageList.get();
        FileDTO fileDTO = message.toFileDTO();
        return List.of(fileDTO);
    }
}