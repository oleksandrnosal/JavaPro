package academy.prog;
import java.util.*;
public class UsersList {
    private static Map<String, Date> onlineUsersList = new HashMap<>();


    public synchronized void getRequestFromUsers(String userLogin) {
        onlineUsersList.put(userLogin, new Date());
    }

    public synchronized List<String> getOnlineUsers() {
        List<String> listOfPresentUsers = new ArrayList<>();
        MessageTime messageTime = new MessageTime();
        for (Map.Entry<String, Date> map: onlineUsersList.entrySet()) {
            if (messageTime.isUserPresent(map.getValue())) {
                listOfPresentUsers.add(map.getKey());
            }
        }
        System.out.println(listOfPresentUsers);
        return listOfPresentUsers;
    }
}

