import java.io.FileOutputStream;
import java.io.IOException;

public class KeyboardReader {
    private byte[] buf;
    public static final int DEFAULT_LENGTH = 255;
    public static final String END = "\\q\n";

    public KeyboardReader() {
        this(DEFAULT_LENGTH);
    }

    public KeyboardReader(int length) {
        // если задали отрицательное значение, что дефолтный размер буфера будет равен 128.
        length = length < 1 ? DEFAULT_LENGTH : length;
        buf =  new byte[length];
    }

    public static void main(String[] args) {
            KeyboardReader kb = new KeyboardReader();
            kb.readToConsole();
            kb.readToFile("C:\\Users\\Дмитрий\\Downloads\\test.txt");
    }

    // функция чтения данных с клавиатуры
    private void readToConsole() {
        int n;
        String tmp;
        try {
          do {
                n = System.in.read(buf);
                // с клавиатуры нельзя получить -, но пусть будет проверка
                if ( n < 0 )
                    return;

                // приведем байтовый массив к строке и выведем на экран
                tmp = new String(buf, 0, n);
                System.out.println(tmp);
                if (tmp.equalsIgnoreCase(END))
                    return;
              } while (true);
            } catch (IOException e) {
                System.out.println("Error #1: " + e.getMessage());
                return;
            }
         // по-хорошему надо так, но это не обязательно
         /*finally {
                try {
                    System.in.close();
                } catch (IOException e) {
                    System.out.println("Error #2: " + e.getMessage());
                    return;
                }
            }
          */
    }

    // запись в файл
    public void readToFile(String fileName) {
        int n;
        String tmp;
        // try-catch-with-resources  !!!
        try (FileOutputStream file = new FileOutputStream(fileName)){
            do {
                n = System.in.read(buf);
                file.write(buf);
                // приведем байтовый массив к строке и выведем на экран
                tmp = new String(buf, 0, n);
                System.out.println(tmp);
                if (tmp.equalsIgnoreCase(END))
                    return;
            } while (true);
        } catch (IOException e) {
            System.out.println("Error #1: " + e.getMessage());
        }
    }
}
