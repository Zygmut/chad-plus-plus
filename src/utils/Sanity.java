package utils;

public class Sanity {

    public int checkInput(String[] args) {

        if (args.length == 0) {
            System.out.println(Env.welcomeString);
            System.exit(0);
        }

        if (!args[1].endsWith(".chpp")) {

        }

        return Env.Ok;
    }

}
