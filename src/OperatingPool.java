import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class OperatingPool {
    public static AtomicInteger count = new AtomicInteger(0);
    LinkedBlockingQueue<Contact> waitingLine = new LinkedBlockingQueue<>();
    int numberOfOperators;

    OperatingPool(int numberOfOperators) {
        this.numberOfOperators = numberOfOperators;
        startOperating(numberOfOperators);
    }

    class Contact {
        String phoneNumber;
        String namePat;

        Contact(String phoneNumber, String namePat) {
            this.phoneNumber = phoneNumber;
            this.namePat = namePat;
        }
    }

    private void contactListCreator() {

        int stringPosition;
        PhoneBook phoneBook = new PhoneBook();
        for (stringPosition = 0; stringPosition < phoneBook.phoneNumberArray.length; stringPosition++) {
            try {
                waitingLine.put(new Contact(phoneBook.phoneNumberArray[stringPosition], phoneBook.namePatArray[stringPosition]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void startOperating(int numberOfOperators) {
        for (int i = 1; i <= numberOfOperators; i++) {
            new Operator().start();
            System.out.println("подключился оператор - " + i);
        }

    }


    class Operator extends Thread {
        public void run() {

            while (count.get() <= 3) {
                Contact contact = waitingLine.poll();
                Random random = new Random();
                int callDuration = random.nextInt(4000);

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
                    count.incrementAndGet();
                    contactListCreator();
                }
            }
            System.out.println("Оператор закончил работу");
        }
    }
}





