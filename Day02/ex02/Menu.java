import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Menu {

    Path curPath;


    public void checkDirectory(Path path) throws FileNotFoundException, NotDirectoryException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException();
        }
        if (!Files.isDirectory(path)) {
            throw new NotDirectoryException(path.toString());
        }
    }

    public void checkAbsolutePath(Path path) throws NotAbsolutePathException {
        if (!path.isAbsolute()) {
            throw new NotAbsolutePathException();
        }
    }

    public Menu(String startPath) throws FileNotFoundException, NotDirectoryException, NotAbsolutePathException {

        this.curPath = Paths.get(startPath);

        checkDirectory(curPath);
        checkAbsolutePath(curPath);
    }

    public void printIncorrect() {
        System.out.println("Incorrect input!");
    }


    public long getFolderSize(File folder) throws IOException {
        long size = 0;
        if( !folder.canRead() ) {
            throw new IOException();
        }
        for (File file : folder.listFiles()) {

            if (file.isFile()) {
                size += file.length();
            } else if (file.isDirectory()) {
                size += getFolderSize(file);
            }
        }
        return size;
    }


    public void executeCommand(String input) throws IOException {
        String[]inputs = input.split("\\s+");
     switch (inputs[0]) {
         case "ls" :
             if (Files.isReadable(curPath)) {
                 Stream<Path> stream = Files.list(curPath);
                 List<Path> pathList = stream.collect(Collectors.toList());
                 for (Path path : pathList) {
                     long size = 0;
                     if (Files.isDirectory(path)) {
                         try {
                             size = getFolderSize(path.toFile());
                         } catch (IOException e) {
                             size = 0;
                         }
                     } else {
                         try {
                             size = Files.size(path);
                         } catch (IOException e) {

                         }
                     }
                     System.out.printf("%s %d KB", path.getFileName(), size / 1024);
                     System.out.println();
                 }
             }
             break;
         case "cd" :
             if (inputs.length != 2) {
                 printIncorrect();
                 break;
             }
             Path path = Paths.get(inputs[1]);
             if (Files.exists(path) && Files.isDirectory(path) && path.isAbsolute() && Files.isExecutable(path)) {
                 this.curPath = path;
             } else {
                 Path tempPath = Paths.get(this.curPath.toString() + "/" + path.toString());
                 if (Files.exists(tempPath) && Files.isDirectory(tempPath) && Files.isExecutable(tempPath)) {
                     this.curPath = Paths.get(this.curPath.toString() + "/" + path.toString());
                 }
             }
             System.out.println(curPath);
             break;
         case "mv" :

             Path pathWhat = Paths.get(curPath + "/" + inputs[1]);
             Path pathWhere = Paths.get(curPath + "/" + inputs[2]);

             if (Files.isRegularFile(pathWhat)) {
                 if (Files.isDirectory(pathWhere)) {
                     pathWhere = Paths.get(pathWhere + "/" + inputs[1]);
                 }
                 Files.move(pathWhat, pathWhere, REPLACE_EXISTING);
             }

             break;
         case "exit" :
             System.out.println("Good bye !");
             System.exit(0);
         default:
             printIncorrect();

     }
    }

    public void start() throws IOException, NotAbsolutePathException {

        String input;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(curPath);
            while ((input = reader.readLine()) != null && !input.equals("exit")) {
                    executeCommand(input);
            }

        }


    }

}
