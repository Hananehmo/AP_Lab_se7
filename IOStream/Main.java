import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Main {

    public static void main(String[] args) throws IOException {
        int input;
        Scanner scanner=new Scanner(System.in);
        File folder = new File("C:\\Users\\Temporary\\Desktop\\Lab Notes");
        File[] listOfFiles = folder.listFiles();
        while(true)
        {
            listOfFiles = folder.listFiles();
            System.out.print("-------------------------------\nChoose Action\n1)New Note\n2)Older Notes\n3)Quit\nChoose:");
            input=scanner.nextInt();
            if(input==1)
            {
                boolean check=true;
                System.out.print("Enter Your New Files Name:");
                String folderName=scanner.next();
                folderName=folderName+".txt";
                while(check)
                {
                    check=false;
                    for(int i=0;i<listOfFiles.length;i++)
                        if(folderName.equals(listOfFiles[i].getName()))
                        {
                            check=true;
                            System.out.println("This File Already Exists\nChoose A New Name:");
                            folderName=scanner.next();
                            folderName=folderName+".txt";
                            break;
                        }
                }
                System.out.println("Enter Text:");
                String text=scanner.nextLine();
                text=scanner.nextLine();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                text=dtf.format(now)+'\n'+text+'\n';
                OutputStream outputStream = new FileOutputStream("C:\\Users\\Temporary\\Desktop\\Lab Notes\\"+folderName);
                for(int i=0;i<text.length();i++)
                outputStream.write(text.charAt(i));
                outputStream.close();
            }
            else if(input==2)
            {
                for(int i=0;i<listOfFiles.length;i++)
                    System.out.println((i+1)+")"+listOfFiles[i].getName());
                System.out.print("Choose Index:");
                int index=scanner.nextInt();
                while (index<1 || index>listOfFiles.length)
                {
                    System.out.print("Choose A Valid Index:");
                    index=scanner.nextInt();
                }
                index--;
                System.out.print("What You Want To Do With This File?\n1)Delete\n2)Add Text\n3)See Full Text\nChoose:");
                int input2=scanner.nextInt();
                while (input2<1 || input2>3)
                {
                    System.out.print("Choose Valid Index:");
                    input2=scanner.nextInt();
                }
                if(input2==1)
                {
                    listOfFiles[index].delete();
                }
                else if(input2==2)
                {
                    System.out.println("Enter The Text You Want To Add:");
                    String text=scanner.nextLine();
                    text=scanner.nextLine();
                    text=text+'\n';
                    File file=new File("C:\\Users\\Temporary\\Desktop\\Lab Notes\\"+listOfFiles[index].getName());
                    try (FileOutputStream fos=new FileOutputStream(new File("C:\\Users\\Temporary\\Desktop\\Lab Notes\\"+listOfFiles[index].getName()),true);) {
                        for(int i=0;i<text.length();i++)
                            fos.write(text.charAt(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(input2==3)
                {
                    File file=new File("C:\\Users\\Temporary\\Desktop\\Lab Notes\\"+listOfFiles[index].getName());
                    InputStream inputstream = new FileInputStream("C:\\Users\\Temporary\\Desktop\\Lab Notes\\"+listOfFiles[index].getName());
                    int data = inputstream.read();
                    while(data != -1) {
                        //do something with data...
                        System.out.print((char) data);
                        data = inputstream.read();
                    }
                    inputstream.close();
                }
            }
            else {
                return;
            }
        }
    }
}
