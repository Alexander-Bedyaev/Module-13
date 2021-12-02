import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Operator extends Thread {
    private final AtomicInteger count = new AtomicInteger(0);
    LinkedBlockingQueue<Contact> waitingLine = new LinkedBlockingQueue<>();


    private class Contact extends Thread {

        String phoneNumber;
        String namePat;

        Contact(String phoneNumber, String namePat) {
            this.phoneNumber = phoneNumber;
            this.namePat = namePat;
        }
    }

    private void contactListCreator() {
        try {
            int stringPosition;
            PhoneBook phoneBook = new PhoneBook();
            for (stringPosition = 0; stringPosition < phoneBook.phoneNumberArray.length; stringPosition++) {
                waitingLine.put(new Contact(phoneBook.phoneNumberArray[stringPosition], phoneBook.namePatArray[stringPosition]));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while (count.get() <= 3) {
            Random random = new Random();
            int callDuration = random.nextInt(4000);
            Contact contact = waitingLine.poll();
            if (contact != null) {
                if (callDuration < 2000) {
                    try {
                        Thread.sleep(callDuration);
                        System.out.println("Абонент " + contact.phoneNumber + " оборвал соединение");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Thread.sleep(callDuration);
                        System.out.println("Абонент " + contact.phoneNumber + " Прослушал сообщение: Уважаемый, " +
                                contact.namePat + ", наша компания прелагает вам...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                contactListCreator();
                count.incrementAndGet();

            }
        }
        try {
            Thread.sleep(4000);
            System.out.println("Оператор закончил работу");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}






