import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PhoneBook {
    private static final String[] nameArray = new String[]{"Петр", "Максим", "Станислав", "Клим", "Александр"};
    private static final String[] namePatArray = new String[nameArray.length];
    private static final String[] phoneNumberArray = new String[nameArray.length];

    private void getNamePatArray() {
        String[] patronymicArray = new String[nameArray.length];
        Random random = new Random();
        String ovich = "ович";
        for (int i = 0; i < patronymicArray.length; i++) {
            patronymicArray[i] = nameArray[i] + ovich;
        }
        for (int i = 0; i < namePatArray.length; i++) {
            int j = random.nextInt(namePatArray.length - 1);
            namePatArray[i] = nameArray[i] + " " + patronymicArray[j];
        }

    }

    private void getPhoneNumbers() {
        Random random = new Random();

        for (int i = 0; i < phoneNumberArray.length; i++) {
            phoneNumberArray[i] = "+7(" + (random.nextInt(99) + 900) + ")" +
                    String.format("%07d", random.nextInt(9999999));
        }

    }

    public void getPhoneBook() {
        getPhoneNumbers();
        getNamePatArray();
        HashMap<String, String> phoneBook = new HashMap<String, String>();
        for (int i = 0; i < phoneNumberArray.length; i++) {
            phoneBook.put(phoneNumberArray[i], namePatArray[i]);
        }

    }

}

