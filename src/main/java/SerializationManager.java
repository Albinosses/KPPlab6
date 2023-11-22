import java.io.*;

public class SerializationManager {
    public static void serializeObject(Object object, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(object);
            System.out.println("Serialization successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object object = inputStream.readObject();
            System.out.println("Deserialization successful.");
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
