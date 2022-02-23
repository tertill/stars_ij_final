import java.io.*;
import java.util.Scanner;

public class Game {

    public Game() {
    }

    public void rungame() {
        player p1 = new player(10);
        p1.setHP(2);
        p1.setBagSpace(5);


        item npc1 = new item(2, "tester one", "a test npc, unknown origins");
        npc1.setIdles("hey!", "hello world!", "i am functional! i am alive!");

        item npc2 = new item(2, "tester two", "a test npc, unknown origins");
        npc2.setIdles("its coming...", "just you wait!", "only a little bit longer...");

        item npc3 = new item(2, "tester three", "a test npc, unknown origins");
        npc3.setIdles("it really is!", "your just uncultured", "imagine liking valorant...");

        item npc4 = new item(2, "tester four", "a test npc, unknown origins");
        npc4.setIdles("...", "i guess you were right...", "wow...");

        item obj1 = new item(1, "apple pie", "a delicious pie with apple filling");
        obj1.setInterType("foodstuffs");
        obj1.setInterEff("+2");

        item obj2 = new item(1, "comfy blanket", "a blanket made from a soft fabric");
        obj2.setInterType("foodstuffs");
        obj2.setInterEff("+1");
        p1.addItem(obj2);

        item obj3 = new item(1, "iron armor", "a protective suit made out of iron");
        obj3.setInterType("armor");
        obj3.setInterEff("+3");

        item obj4 = new item(1, "fabric", "the same fabric used to make your bag. maybe it can be used to expand your bag space?");
        obj4.setInterType("foodstuffs");
        obj4.setInterEff("+3");

        building room1 = new building("tavern", 1);

        building room2 = new building("your ship", 2);

        building room3 = new building("the fountain", 3);

        int day = 1;

        try (Scanner input = new Scanner(System.in);) {
            room1.addItem(npc1);
            room1.addItem(npc2);
            room1.addItem(obj1);


            room2.addItem(npc3);
            room2.addItem(obj2);
            room2.addItem(obj3);
            room2.addItem(obj4);

            //-----

            area world = new area("world 1", "town", 1, 1);

            world.addBuilding(room1);
            world.addBuilding(room2);
            world.addBuilding(room3);

            quest main1 = new quest("talk to tester 4!", "find tester 4 and talk some sense into him!", 1);
            quest extra1 = new quest("bring me something tasty!", "bring tester 2 something sweet to eat!", 0);

            world.addQuests(main1, 0);
            world.addQuests(extra1, 1);

            world.getHome()[0] = p1;

            boolean run = true;

            // WELCOME SCREEN!!!!!!!!!!!
            System.out.println("                            _                                  __                             _  _                  \r\n"
                    + "  __ _      o O O   ___    | |_    __ _      _ _     o O O    / _|   ___      _ _     o O O  | || |   ___    _  _   \r\n"
                    + " / _` |    o       (_-<    |  _|  / _` |    | '_|   o        |  _|  / _ \\    | '_|   o        \\_, |  / _ \\  | +| |  \r\n"
                    + " \\__,_|   TS__[O]  /__/_   _\\__|  \\__,_|   _|_|_   TS__[O]  _|_|_   \\___/   _|_|_   TS__[O]  _|__/   \\___/   \\_,_|  \r\n"
                    + "_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_| \"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \r\n"
                    + "\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-' ");

            boolean opening = true;

            System.out.println("[n + enter] new game");

            System.out.println("[c + enter] continue");

            while (opening) {

                System.out.println("what will u do");

                String opener = input.nextLine();

                //String opener = "n";

                //System.out.println("starting new game...");

                if (opener.equals("n")) {

                    /* If the player wants to start a new game:
                     * first, wipe any current save file present on the save.txt file
                     * second, boot into the game
                     */

                    try {
                        String s1;
                        //Scanner scan = new Scanner("save//save_01.txt");

                        BufferedReader scan = new BufferedReader(new FileReader("save//save_01.txt"));
                        StringBuffer sb = new StringBuffer();
                        while (scan.readLine() != null) {
                            s1 = scan.readLine();
                            sb.append(s1);
                        }

                        String result = sb.toString();

                        result = result.replaceAll("save//save_01.txt", "");

                        PrintWriter w1 = new PrintWriter("save//save_01.txt");

                        w1.append(result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    opening = false;

                } else if (opener.equals("c")) {

                    /* If the player wants to continue their game:
                     * before everything, check if there even is a save file
                     * first, pull up the save code and save it to a string
                     * look at the first digit, place the player in the corresponding area
                     * look at the second digit, place the player in the corresponding building
                     * substring pos 3 to 6, load in player health
                     * do the same for fatigue, substring pos 7 to 10
                     * use substring and import the items back into the players bag based on the digits
                     * set the npc stats to see if the player has talked to them yet
                     * set the quest statuses
                     */

                    try {
                        BufferedReader scan = new BufferedReader(new FileReader("save//save_01.txt"));

                        String code = scan.readLine();

                        if (code != null) { //if there is a save file present

                            int area = Integer.parseInt(code.substring(0, 1)); // grab the player location

                            // add the player
                            if (area == 1) {
                                world.getHome()[0] = p1;
                            }

                            int building = Integer.parseInt(code.substring(1, 2));

                            if (building != 0) {
                                world.removePlayer();

                                if (building == 1) {
                                    building--;

                                    world.getRooms()[building].getHome()[0] = p1;

                                } else if (building == 2) {
                                    building--;

                                    world.getRooms()[building].getHome()[0] = p1;

                                } else {

                                    System.out.println("something went wrong...");

                                }
                            }


                            // load in player health

                            p1.setHP(Integer.parseInt(code.substring(3, 4))); // actual health

                            p1.setMhealth(Integer.parseInt(code.substring(5, 6))); // max health

                            // load in player fatigue

                            p1.setFat(Integer.parseInt(code.substring(7, 8)));

                            // load in the players items

                            // load in item block digit 1 (apple pies)

                            for (int i = Integer.parseInt(code.substring(11, 12)); i > 0; i--) {

                                if (p1.getBag()[i] != null) {
                                    p1.getBag()[i] = obj1;
                                }

                            }

                            // load in item block digit 2 (comfy blankets)

                            for (int i = Integer.parseInt(code.substring(12, 13)); i > 0; i--) {

                                if (p1.getBag()[i] != null) {
                                    p1.getBag()[i] = obj2;
                                }

                            }

                            // load in item block digit 3 (iron armor)

                            for (int i = Integer.parseInt(code.substring(13, 14)); i > 0; i--) {

                                if (p1.getBag()[i] != null) {
                                    p1.getBag()[i] = obj3;
                                }

                            }

                            // load in item block digit 4 (fabric pieces)

                            for (int i = Integer.parseInt(code.substring(14, 15)); i > 0; i--) {

                                if (p1.getBag()[i] != null) {
                                    p1.getBag()[i] = obj4;
                                }

                            }

                            // load in npc stats

                            // npc1 state

                            boolean s1 = Integer.parseInt(code.substring(17, 18)) == 1;

                            npc1.setspoken(s1);

                            // npc2 state

                            boolean s2 = Integer.parseInt(code.substring(18, 19)) == 1;

                            npc2.setspoken(s2);

                            // npc3 state

                            boolean s3 = Integer.parseInt(code.substring(19, 20)) == 1;

                            npc3.setspoken(s3);

                            // npc4 state

                            boolean s4 = Integer.parseInt(code.substring(20, 21)) == 1;

                            npc4.setspoken(s4);

                            // finally, load in quest statuses

                            // to give the player the quest
                            boolean hasm1 = Integer.parseInt(code.substring(23, 24)) == 1;

                            if (hasm1) {
                                p1.addQuest(main1);
                            }

                            boolean hase1 = Integer.parseInt(code.substring(26, 27)) == 1;

                            if (hase1) {
                                p1.addQuest(extra1);
                            }

                            // set the states of the quests
                            boolean eq1 = Integer.parseInt(code.substring(27, 28)) == 1;

                            extra1.setstate(eq1);

                            boolean mq1 = Integer.parseInt(code.substring(24, 25)) == 1;

                            main1.setstate(mq1);
                            // that should be all? no it aint

                            // finally load in date and time

                            day = Integer.parseInt(code.substring(29, 30)); // date

                            p1.setActions(Integer.parseInt(code.substring(30, 31))); // actions

                        } else {

                            System.out.println("there is no save file to load");

                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    opening = false;
                } else {

                    System.out.println("please enter a valid option");

                }
            }


            while(run) { // GAME STARTS HERE!!!!!!!!!!!
                String user;
                int reg;

                int convert = -1;// this will be used for user input later on
                int opt = -1; // this will be used to gauge if the user input is a valid option or not

                // check the time of day to find out how many actions the player can do

                if (day == 14) { // end conditions, day 14 is the last day
                    run = false;
                }

                if (p1.getActions() == 0) {

                    p1.setTime("dawn");

                    p1.setFat(0);

                } else if (p1.getActions() >= 1 && p1.getActions() < 5) {

                    p1.setTime("day");

                } else if (p1.getActions() >= 5 && p1.getActions() < 8) {

                    p1.setTime("afternoon");

                } else  if (p1.getActions() >= 8 && p1.getActions() < 10){

                    p1.setTime("night");

                    p1.setFat(4);

                } else {

                    System.out.println("you are really tired... you feel like your about to collapse...");

                    //advance time

                    System.out.println("advancing to the next day...");
                    day++;

                    p1.setActions(0);

                }

                //find the player location
                boolean w1 = false;
                boolean r1 = false;
                boolean r2 = false;
                boolean r3 = false;

                if (world.isHere()) { // if the player exists in the world 1 area, then...
                    opt = world.count(); // this sets opt - the number of options for the player to the indexed number of buildings in the world 1 area

                    world.options(p1, day, p1.getTime()); // prints out the options the player has

                    w1 = true; // sets the w1 variable to true, letting the program know that the player is in world 1 area right now
                }

                if (room1.isHere()) { // if the player exists in room 1 building, then...
                    opt = room1.count(); // sets opt equal to the indexed number of items in the room 1 building

                    room1.options(world, p1, day, p1.getTime()); // prints out the options the player has

                    r1 = true; // sets the r1 variable to true, letting the program know that the player is in room 1 building right now
                }

                if (room2.isHere()) { // if the player exists in room 2 building, then...
                    opt = room2.count(); // sets opt equal to the indexed number of items in the room 2 building

                    room2.options(world, p1, day, p1.getTime()); // prints out the options the player has

                    r2 = true; // sets the r2 variable to true, letting the program know that the player is in room 2 building right now
                }

                if (room3.isHere()) { // if the player exists in room 3 building, then...
                    opt = room3.count(); // sets opt equal to the indexed number of items in the room 2 building

                    room3.options(world, p1, day, p1.getTime()); // prints out the options the player has

                    r3 = true; // sets the r3 variable to true, letting the program know that the player is in room 2 building right now
                }

                if (p1.getQuests()[0] != null) { // if the player has the main quest

                    int tracker = 0;

                    for (int i = 0; i < room3.getItems().length; i++) {

                        if (room3.getItems()[i] != null) {
                            if (room3.getItems()[i].equals(npc4)) {

                                tracker++;

                            }
                        }
                    }

                    if (tracker == 0) {
                        room3.addItem(npc4); // add the npc4, making him available to talk to
                    }


                }





                user = input.next(); // take the user input

                if (user.equals("0") || user.equals("1") || user.equals("2") || user.equals("3") || user.equals("4") || user.equals("5")) {

                    convert = Integer.parseInt(user);

                }


                if (convert >= 0 && convert <= opt) { // if the user input is in bounds, then...

                    if (w1) { // if the player is in world 1 area, then...

                        world.getRooms()[convert].getHome()[0] = world.removePlayer(); // remove the player from world 1 area, and move them into selected building

                    } else if (r1) { // if the player is in room 1 building, then... (ROOM 1 CODE STARTS HERE!!!!!!!!!) vvvv

                        if (room1.getItems()[convert].getType() == 1) { // if the selected item is an interactable (type 1), then...

                            interactoptions(room1, p1, convert, input); // interact with the selected item (see interactoptions method for details)

                        } else { // the object is of type 2 (an npc), so...

                            item npc = room1.getItems()[convert];

                            if (npc.getName().equals("tester one")) { // if the selected npc is tester one

                                //---
                                // start conversation here

                                if (npc.hasTalked() == false) {

                                    try {
                                        BufferedReader reader = new BufferedReader(new FileReader("dialogue\\testerone.txt"));

                                        String text = reader.readLine(); // contains text from the dialogue.txt

                                        while (text != null) { // if there is dialogue in this spot, then


                                            if (text.substring(0, 1).equals(">")) { // if the line has the player prefix, then...

                                                System.out.println(p1.speak(text.substring(1))); // print out line with player

                                            } else if (text.substring(0, 1).equals("}")) { // if the dialogue is a choice, then..

                                                System.out.println(npc.speak(text.substring(1))); // print out line

                                                text = reader.readLine(); // move the buffered reader one line down

                                                while (text != null) { // if theres stuff on this line
                                                    if (text.substring(0, 1).equals("[")) { // if it has the choice prefix ([x + enter] do something)
                                                        System.out.println(text); // print out the choice

                                                        text = reader.readLine(); // move the buffered reader one line down
                                                    }

                                                }

                                                user = input.next(); // ask for user input


                                                if (user.equals("0")) { // if the player is enjoying the test, then...

                                                    System.out.println(npc.speak("great!"));
                                                    System.out.println(npc.speak("thanks for the feedback *"));

                                                } else { // the player isnt enjoying the test, so...

                                                    System.out.println(npc.speak("oh..."));
                                                    System.out.println(npc.speak("thanks for the feedback *"));

                                                }

                                            } else { // so the dialogue is just a normal line to be said by an npc

                                                System.out.println(npc.speak(text)); // print out line with npc format

                                            }

                                            user = input.next();

                                            text = reader.readLine();
                                        }

                                        reader.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    npc.setspoken(true); // let the program know that the npc has already said all of its major dialogue

                                } else { // the player has already exhausted all of this npc's major dialogue
                                    System.out.println(npc.idle() + " *"); // print out an idle dialogue
                                }

                                p1.setActions(p1.getActions() + 1);

                            }

                            if (npc.getName().equals("tester two")) { // if the selected npc is tester two

                                //---
                                // start conversation here

                                if (npc.hasTalked() == false) {

                                    try {
                                        BufferedReader reader = new BufferedReader(new FileReader("dialogue\\testertwo.txt"));

                                        String text = reader.readLine(); // contains text from the dialogue.txt

                                        while (text != null) { // if there is dialogue in this spot, then


                                            if (text.substring(0, 1).equals(">")) { // if the line has the player prefix, then...

                                                System.out.println(p1.speak(text.substring(1))); // print out line with player

                                            } else { // so the dialogue is just a normal line to be said by an npc

                                                System.out.println(npc.speak(text)); // print out line with npc format

                                            }

                                            user = input.next();

                                            text = reader.readLine();
                                        }

                                        reader.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    npc.setspoken(true);

                                } else if (main1.isComplete()) { // if the player has completed the main1 quest

                                    System.out.println(npc.speak("hello player! *"));

                                    user = input.next();

                                    System.out.println(npc.speak("i am quite famished and require something sweet to eat. can you bring me an apple pie? *"));

                                    p1.addQuest(extra1);

                                    System.out.println("you recieved the " + extra1.getName() + " extra quest! *");

                                    user = input.next();

                                    System.out.println(npc.speak("do you have it yet?\n[0 + enter] yeah\n[1 + enter] nah"));

                                    user = input.next();

                                    if (user.equals("0")) { // the player says they have the apple pie

                                        System.out.println(npc.speak("ok cool! can i have it?"));

                                        p1.printBag();

                                        System.out.print("\nenter the number of the item you want to give to " + npc.getName() + " :");

                                        convert = input.nextInt();

                                        if (p1.getBag()[convert].getName().equals("apple pie")) { // if the selected item is an apple pie

                                            p1.getBag()[convert] = null; // remove the apple pie

                                            System.out.println(npc.speak("thanks! hey looks like youve done everything important *"));

                                            user = input.next();

                                            extra1.setstate(true); // set the quest to complete

                                            System.out.println(npc.speak("thanks for playing! *"));

                                            user = input.next();

                                        } else {

                                            System.out.println(npc.speak("hey... this isnt an apple pie. *"));

                                            user = input.next();
                                        }

                                    } else { // the player says they dont have it yet

                                        System.out.println(npc.speak("its ok take your time *"));

                                    }

                                } else {
                                    System.out.println(npc.idle() + " *");

                                    user = input.next();
                                }
                            }

                            p1.setActions(p1.getActions() + 1);
                        }

                    } else if (r2) { // the player is in room 2, so... (ROOM 2 CODE STARTS HERE!!!!!!!!!!) vvv

                        if (room2.getItems()[convert].getType() == 1) { // if the selected item is an interactable (type 1), then...

                            interactoptions(room2, p1, convert, input); // interact with the selected item (see interactoptions method for details)

                        } else { // the item is an npc, so

                            item npc = room2.getItems()[convert];

                            if (npc.getName().equals("tester three")) {

                                if (npc.hasTalked() == false && main1.isComplete() == false) {
                                    try {
                                        BufferedReader reader = new BufferedReader(new FileReader("dialogue\\testerthree.txt"));

                                        String text = reader.readLine(); // contains text from the dialogue.txt

                                        while (text != null) { // if there is dialogue in this spot, then


                                            if (text.substring(0, 1).equals(">")) { // if the line has the player prefix, then...

                                                System.out.println(p1.speak(text.substring(1))); // print out line with player

                                            } else { // so the dialogue is just a normal line to be said by an npc

                                                System.out.println(npc.speak(text)); // print out line with npc format

                                            }

                                            user = input.next();

                                            text = reader.readLine();
                                        }

                                        reader.close();

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    p1.getQuests()[0] = main1;

                                    System.out.println("you recieved the " + p1.getQuests()[0].getName() + " main quest");

                                    npc.setspoken(true);

                                } else if (main1.isComplete() == true) { // if the player has completed the main quest, then goes back to talk to tester three

                                    System.out.println(npc3.speak("hey! thanks for earlier! thats great that hes finally learning how to think properly. here, take this-  "));

                                    p1.addItem(obj4);

                                    System.out.println("\nyou got a(n) " + obj4.getName());

                                    System.out.println(npc3.speak("its a piece of fabric - dont worry its on the house *"));

                                    user = input.next();

                                    System.out.println(npc3.speak("you can use it to expand the space in your bag *!"));

                                    user = input.next();

                                    System.out.println("hey, why dont you go talk to the other guys again? they might have some problems to solve... *");

                                    user = input.next();

                                } else {

                                    System.out.println(npc.speak(npc.idle()));

                                }


                                p1.setActions(p1.getActions() + 1);
                            }


                        }
                    } else { // the player is in room three, so... (ROOM 3 CODE STARTS HERE!!!!! vvvvv)

                        if (room3.getItems()[convert].getType() == 1) { // the selected item is an interactable

                            interactoptions(room3, p1, convert, input);

                        } else { // the selected item is an npc

                            item npc = room3.getItems()[convert];

                            if (npc.getName().equals("tester four")) {

                                if (npc.hasTalked() == false) {
                                    try {
                                        BufferedReader reader = new BufferedReader(new FileReader("dialogue\\t4mq.txt"));

                                        String text = reader.readLine(); // contains text from the dialogue.txt

                                        while (text != null) { // if there is dialogue in this spot, then


                                            if (text.substring(0, 1).equals(">")) { // if the line has the player prefix, then...

                                                System.out.println(p1.speak(text.substring(1))); // print out line with player

                                            } else { // so the dialogue is just a normal line to be said by an npc

                                                System.out.println(npc.speak(text)); // print out line with npc format

                                            }

                                            user = input.next();

                                            text = reader.readLine();
                                        }

                                        reader.close();

                                        boolean won = false;

                                        while (won == false) {

                                            won = cards(p1, npc, input);

                                        }

                                        System.out.println(npc4.speak("\nwow... i guess you guys were right. can you go tell tester three i said sorry? *"));

                                        user = input.next();

                                        main1.setstate(true);

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }



                                    npc.setspoken(true);
                                } else {

                                    System.out.println(npc.speak(npc.idle()));

                                }


                                p1.setActions(p1.getActions() + 1);

                            }
                        }

                    }

                } else if (user.equals("x")) { // if the player wants to leave

                    if (w1) { // if the player is in the world 1 area, then
                        System.out.println("there aren't any other areas that you can access right now, so you can't do that. *");

                        user = input.next();
                    } else if (r1 || r2 || r3) {

                        if (r1) { // if the player is in the room 1 building
                            world.getHome()[0] = room1.removePlayer(); // remove the player and put them in world 1 area
                        } else if (r2){ // the player is in room 2 building
                            world.getHome()[0] = room2.removePlayer();
                        } else {
                            world.getHome()[0] = room3.removePlayer();
                        }

                    } else {
                        System.out.println("something went wrong...");
                    }

                } else if (user.equals("s")) { // if the player wants to save their progress

                    if (r2) { // if the player is in room 2, or your ship

                        save(p1, world, r1, r2, room1, room2, npc1, npc2, npc3, npc4, obj1, obj2, obj3, obj4, user, input, day, p1.getTime());

                        //advance time

                        System.out.println("advancing to the next day...");
                        day++;

                        p1.setActions(0);
                    }

                } else if (user.equals("e")) { // if the player wants to check their bag
                    p1.printBagWP();

                    user = input.next();

                    if (user.equals("x")) { // the player wants to throw something away
                        System.out.print("enter the number of the item you want to throw away: ");

                        convert = input.nextInt();

                        System.out.println(p1.getBag()[convert].getName() + " has been discarded");

                        p1.getBag()[convert] = null;
                    }

                    if (user.equals("f")) { // the player wants to interact with something

                        System.out.println("\nenter the number of the item you want to interact with: ");
                        convert = input.nextInt();

                        if (p1.getBag()[convert].getInterType().equals("foodstuffs")) { // if the selected interactable is a foodstuffs item, then...

                            foodoptions(convert, p1, input);

                        } else { // the item is a piece of armor

                            int put = convert;

                            String effect = room1.getItems()[convert].getInterEff();

                            System.out.println("what will you do?\n[0 + enter] put it on\n[1 + enter] put it in your bag");

                            convert = input.nextInt();

                            if (convert == 0) { // if the player wants to put it on, then...

                                if (effect.substring(1, 2).equals("1")) { // if the armor only adds one durability point, then...

                                    p1.setMhealth(p1.getMhealth() + 1);

                                    System.out.println("your max health was increased by 1");

                                } else if (effect.substring(1, 2).equals("2")) { // if the armor adds two durability points, then...

                                    p1.setMhealth(p1.getMhealth() + 2);

                                    System.out.println("your max health was increased by 2");

                                } else if (effect.substring(1, 2).equals("3")) { // if the armor adds three durability points, then...

                                    p1.setMhealth(p1.getMhealth() + 3);

                                    System.out.println("your max health was increased by 3");

                                } else {

                                    System.out.println("you put it on, but nothing happened...");

                                }

                                room1.getItems()[put] = null; // remove the item from the list, since the player is now wearing it

                            } else { // the player wants to put it in their bag, so...

                                int tracker = 0; // sees how many times specified item has been added to the bag

                                for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                                    if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                                        p1.getBag()[i] = room1.getItems()[put]; // put the specified item into that spot

                                        tracker++; // increment the tracker to let the program know the item is in the bag now

                                        System.out.println(room1.getItems()[put].getName() + " has been added to your bag");

                                        room1.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                                    }

                                }

                                if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                                    System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                                    convert = input.nextInt();

                                    if (convert == 0) { // if the player wants to throw away an item, then...
                                        //first print out the list of items in the bag
                                        p1.printBag();

                                        System.out.print("[number of the item you want to throw away + enter]:  ");

                                        convert = input.nextInt();

                                        System.out.println(p1.getBag()[convert].getName() + " was discarded");

                                        p1.getBag()[convert] = null; // remove the item in the specified spot

                                        p1.getBag()[convert] = room1.getItems()[put]; // put the item in the emptied spot

                                        System.out.println(room1.getItems()[put].getName() + " has been added to your bag");

                                        room1.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                                    } else { // the player wants to discard the item, not keep it
                                        System.out.println(room1.getItems()[put].getName() + " was discarded");
                                    }
                                }
                            }
                        }
                    }

                } else if (user.equals("r")) { // if the player wants to go resource gathering

                    int rand = (int)(Math.random() * (5 - 1) + 1); // generate a random number from 1 to 4 to determine what item to give the player

                    if (p1.getFat() != 5) { // if the player isnt completely fatigued

                        if (rand == 1) { // if the random number is 1, then

                            int tracker = 0;

                            for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag to find an empty spot
                                if (tracker == 0 && p1.getBag()[i] == null) {
                                    p1.getBag()[i] = obj1; // and put it in there

                                    System.out.println("you found a(n) " + obj1.getName());

                                    System.out.println(obj1.getName() + " has been added to your bag");

                                    tracker++; // then let the program know that the item is now in the players bag
                                }
                            }

                            if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                                System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                                convert = input.nextInt();

                                if (convert == 0) { // if the player wants to throw away an item, then...
                                    //first print out the list of items in the bag
                                    p1.printBag();

                                    System.out.print("[number of the item you want to throw away + enter]:  ");

                                    convert = input.nextInt();

                                    System.out.println(p1.getBag()[convert].getName() + " was discarded");

                                    p1.getBag()[convert] = null; // remove the item in the specified spot

                                    p1.getBag()[convert] = obj1; // put the item in the emptied spot

                                    System.out.println(obj1.getName() + " has been added to your bag");

                                } else { // the player wants to discard the item, not keep it
                                    System.out.println(obj1.getName() + " was discarded");
                                }
                            }


                        } else if (rand == 2) { // if the random number is 2

                            int tracker = 0;

                            for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag to find an empty spot
                                if (tracker == 0 && p1.getBag()[i] == null) {
                                    p1.getBag()[i] = obj2; // and put it in there

                                    System.out.println("you found a(n) " + obj2.getName());

                                    System.out.println(obj2.getName() + " has been added to your bag");

                                    tracker++; // then let the program know that the item is now in the players bag
                                }
                            }

                            if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                                System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                                convert = input.nextInt();

                                if (convert == 0) { // if the player wants to throw away an item, then...
                                    //first print out the list of items in the bag
                                    p1.printBag();

                                    System.out.print("[number of the item you want to throw away + enter]:  ");

                                    convert = input.nextInt();

                                    System.out.println(p1.getBag()[convert].getName() + " was discarded");

                                    p1.getBag()[convert] = null; // remove the item in the specified spot

                                    p1.getBag()[convert] = obj2; // put the item in the emptied spot

                                    System.out.println(obj2.getName() + " has been added to your bag");

                                } else { // the player wants to discard the item, not keep it
                                    System.out.println(obj2.getName() + " was discarded");
                                }
                            }

                        } else if (rand == 3) { // if the random number is 3
                            int tracker = 0;

                            for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag to find an empty spot
                                if (tracker == 0 && p1.getBag()[i] == null) {
                                    p1.getBag()[i] = obj3; // and put it in there

                                    System.out.println("you found a(n) " + obj3.getName());

                                    System.out.println(obj3.getName() + " has been added to your bag");

                                    tracker++; // then let the program know that the item is now in the players bag
                                }
                            }

                            if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                                System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                                convert = input.nextInt();

                                if (convert == 0) { // if the player wants to throw away an item, then...
                                    //first print out the list of items in the bag
                                    p1.printBag();

                                    System.out.print("[number of the item you want to throw away + enter]:  ");

                                    convert = input.nextInt();

                                    System.out.println(p1.getBag()[convert].getName() + " was discarded");

                                    p1.getBag()[convert] = null; // remove the item in the specified spot

                                    p1.getBag()[convert] = obj3; // put the item in the emptied spot

                                    System.out.println(obj3.getName() + " has been added to your bag");

                                } else { // the player wants to discard the item, not keep it
                                    System.out.println(obj3.getName() + " was discarded");
                                }
                            }


                        } else { // if the random number isn't 1-3

                            System.out.println("you didnt find anything...");

                        }

                        p1.setFat(p1.getFat() + 1);

                    }


                } else if (user.equals("q")) {

                    System.out.println("//--------------------\n\n");

                    p1.printQuests();

                } else {

                    System.out.println("please enter a valid option");

                }

                // 300 1 21
            }
        }
    }

    // foodoptions: check bag version
    public void foodoptions (int ui, player p1, Scanner scan) {

        int put = ui;

        System.out.println("//--------------------\n\n");

        if ( !(p1.getBag()[put].getName().equals("fabric")) ) {

            System.out.println("what will you do?\n[0 + enter] eat it\n[1 + enter] put it in your bag");

            ui = scan.nextInt();

            if (ui == 0) { // if the player chooses to eat it, then

                if (p1.getHP() == p1.getMhealth()) { // if the player is at full health, then...

                    System.out.println("you ate it, but nothing happened...");

                } else { // the players health is not full, so...

                    String effect = p1.getBag()[put].getInterEff();

                    if (effect.substring(0, 1).equals("+")) { // if the food heals, then...

                        if (effect.substring(1, 2).equals("1")) { // if the food only heals 1 hp, then...

                            p1.setHP(p1.getHP() + 1); // add 1 hp to the players health

                            System.out.println("1 hp was restored"); // print how much hp was restored

                        } else if (effect.substring(1, 2).equals("2")) { // if the food heals 2 hp, then...

                            int track = 0;

                            if (p1.getHP() + 2 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 2);

                                track = 2;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else if (effect.substring(1, 2).equals("3")) { // if the food heals 3 hp, then...

                            int track = 0;

                            if (p1.getHP() + 3 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 3);

                                track = 3;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else {
                            System.out.println("nothing happened...");
                        }

                    }

                }

            } else { // the player chooses to put it in their bag

                int tracker = 0; // sees how many times specified item has been added to the bag

                for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                    if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                        p1.getBag()[i] = p1.getBag()[put]; // put the specified item into that spot

                        tracker++; // increment the tracker to let the program know the item is in the bag now

                        System.out.println(p1.getBag()[put].getName() + " has been added to your bag");

                        p1.getBag()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                    }

                }

                if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                    System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                    ui = scan.nextInt();

                    if (ui == 0) { // if the player wants to throw away an item, then...
                        //first print out the list of items in the bag
                        p1.printBag();

                        System.out.print("[number of the item you want to throw away + enter]:  ");

                        ui = scan.nextInt();

                        System.out.println(p1.getBag()[ui].getName() + " was discarded");

                        p1.getBag()[ui] = null; // remove the item in the specified spot

                        p1.getBag()[ui] = p1.getBag()[put]; // put the item in the emptied spot

                        System.out.println(p1.getBag()[put].getName() + " has been added to your bag");

                        p1.getBag()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                    } else { // the player wants to discard the item, not keep it
                        System.out.println(p1.getBag()[put].getName() + " was discarded");
                    }
                }
            }

        } else {

            System.out.println("what will you do?\n[0 + enter] eat it\n[1 + enter] put it in your bag\n[2 + enter] expand your bag");

            ui = scan.nextInt();

            if (ui == 0) { // if the player chooses to eat it, then

                if (p1.getHP() == p1.getMhealth()) { // if the player is at full health, then...

                    System.out.println("you ate it, but nothing happened...");

                } else { // the players health is not full, so...

                    String effect = p1.getBag()[put].getInterEff();

                    if (effect.substring(0, 1).equals("+")) { // if the food heals, then...

                        if (effect.substring(1, 2).equals("1")) { // if the food only heals 1 hp, then...

                            p1.setHP(p1.getHP() + 1); // add 1 hp to the players health

                            System.out.println("1 hp was restored"); // print how much hp was restored

                        } else if (effect.substring(1, 2).equals("2")) { // if the food heals 2 hp, then...

                            int track = 0;

                            if (p1.getHP() + 2 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 2);

                                track = 2;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else if (effect.substring(1, 2).equals("3")) { // if the food heals 3 hp, then...

                            int track = 0;

                            if (p1.getHP() + 3 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 3);

                                track = 3;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else {
                            System.out.println("nothing happened...");
                        }

                    }

                }

            } else if (ui == 1) { // the player chooses to put it in their bag

                int tracker = 0; // sees how many times specified item has been added to the bag

                for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                    if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                        p1.getBag()[i] = p1.getBag()[put]; // put the specified item into that spot

                        tracker++; // increment the tracker to let the program know the item is in the bag now

                        System.out.println(p1.getBag()[put].getName() + " has been added to your bag");

                        p1.getBag()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                    }

                }

                if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                    System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                    ui = scan.nextInt();

                    if (ui == 0) { // if the player wants to throw away an item, then...
                        //first print out the list of items in the bag
                        p1.printBag();

                        System.out.print("[number of the item you want to throw away + enter]:  ");

                        ui = scan.nextInt();

                        System.out.println(p1.getBag()[ui].getName() + " was discarded");

                        p1.getBag()[ui] = null; // remove the item in the specified spot

                        p1.getBag()[ui] = p1.getBag()[put]; // put the item in the emptied spot

                        System.out.println(p1.getBag()[put].getName() + " has been added to your bag");

                        p1.getBag()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                    } else { // the player wants to discard the item, not keep it
                        System.out.println(p1.getBag()[put].getName() + " was discarded");
                    }
                }

            } else { // the player chooses to expand their bag

                p1.setBagSpace(p1.getBagSpace() + 5);

                System.out.println("your bag can now hold 5 more things!");

            }
        }
    }

    // foodoptions: room interaction version
    public void foodoptions (int ui, player p1, building room, Scanner scan) {
        int put = ui;

        System.out.println("//--------------------\n\n");

        if ( !(room.getItems()[ui].getName().equals("fabric")) ) {

            System.out.println("what will you do?\n[0 + enter] eat it\n[1 + enter] put it in your bag");

            ui = scan.nextInt();

            if (ui == 0) { // if the player chooses to eat it, then

                if (p1.getHP() == p1.getMhealth()) { // if the player is at full health, then...

                    System.out.println("you ate it, but nothing happened...");

                } else { // the players health is not full, so...

                    String effect = room.getItems()[put].getInterEff();

                    if (effect.substring(0, 1).equals("+")) { // if the food heals, then...

                        if (effect.substring(1, 2).equals("1")) { // if the food only heals 1 hp, then...

                            p1.setHP(p1.getHP() + 1); // add 1 hp to the players health

                            System.out.println("1 hp was restored"); // print how much hp was restored

                        } else if (effect.substring(1, 2).equals("2")) { // if the food heals 2 hp, then...

                            int track = 0;

                            if (p1.getHP() + 2 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 2);

                                track = 2;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else if (effect.substring(1, 2).equals("3")) { // if the food heals 3 hp, then...

                            int track = 0;

                            if (p1.getHP() + 3 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 3);

                                track = 3;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else {
                            System.out.println("nothing happened...");
                        }

                    }

                }

            } else { // the player chooses to put it in their bag

                int tracker = 0; // sees how many times specified item has been added to the bag

                for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                    if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                        p1.getBag()[i] = room.getItems()[put]; // put the specified item into that spot

                        tracker++; // increment the tracker to let the program know the item is in the bag now

                        System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                        room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                    }

                }

                if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                    System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                    ui = scan.nextInt();

                    if (ui == 0) { // if the player wants to throw away an item, then...
                        //first print out the list of items in the bag
                        p1.printBag();

                        System.out.print("[number of the item you want to throw away + enter]:  ");

                        ui = scan.nextInt();

                        System.out.println(p1.getBag()[ui].getName() + " was discarded");

                        p1.getBag()[ui] = null; // remove the item in the specified spot

                        p1.getBag()[ui] = room.getItems()[put]; // put the item in the emptied spot

                        System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                        room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                    } else { // the player wants to discard the item, not keep it
                        System.out.println(room.getItems()[put].getName() + " was discarded");
                    }
                }
            }

        } else {

            System.out.println("what will you do?\n[0 + enter] eat it\n[1 + enter] put it in your bag\n[2 + enter] expand your bag");

            ui = scan.nextInt();

            if (ui == 0) { // if the player chooses to eat it, then

                if (p1.getHP() == p1.getMhealth()) { // if the player is at full health, then...

                    System.out.println("you ate it, but nothing happened...");

                } else { // the players health is not full, so...

                    String effect = room.getItems()[put].getInterEff();

                    if (effect.substring(0, 1).equals("+")) { // if the food heals, then...

                        if (effect.substring(1, 2).equals("1")) { // if the food only heals 1 hp, then...

                            p1.setHP(p1.getHP() + 1); // add 1 hp to the players health

                            System.out.println("1 hp was restored"); // print how much hp was restored

                        } else if (effect.substring(1, 2).equals("2")) { // if the food heals 2 hp, then...

                            int track = 0;

                            if (p1.getHP() + 2 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 2);

                                track = 2;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else if (effect.substring(1, 2).equals("3")) { // if the food heals 3 hp, then...

                            int track = 0;

                            if (p1.getHP() + 3 > p1.getMhealth()) { // if this item makes the players health bigger than their max health, set hp to max health
                                track = p1.getMhealth() - p1.getHP();

                                p1.setHP(p1.getMhealth());

                            } else {
                                p1.setHP(p1.getHP() + 3);

                                track = 3;
                            }

                            System.out.println(track + " hp was restored"); // print how much hp was restored
                        } else {
                            System.out.println("nothing happened...");
                        }

                    }

                }

            } else if (ui == 1) { // the player chooses to put it in their bag

                int tracker = 0; // sees how many times specified item has been added to the bag

                for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                    if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                        p1.getBag()[i] = room.getItems()[put]; // put the specified item into that spot

                        tracker++; // increment the tracker to let the program know the item is in the bag now

                        System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                        room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                    }

                }

                if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                    System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                    ui = scan.nextInt();

                    if (ui == 0) { // if the player wants to throw away an item, then...
                        //first print out the list of items in the bag
                        p1.printBag();

                        System.out.print("[number of the item you want to throw away + enter]:  ");

                        ui = scan.nextInt();

                        System.out.println(p1.getBag()[ui].getName() + " was discarded");

                        p1.getBag()[ui] = null; // remove the item in the specified spot

                        p1.getBag()[ui] = room.getItems()[put]; // put the item in the emptied spot

                        System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                        room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                    } else { // the player wants to discard the item, not keep it
                        System.out.println(room.getItems()[put].getName() + " was discarded");
                    }
                }

            } else { // the player chooses to expand their bag

                p1.setBagSpace(p1.getBagSpace() + 5);

                System.out.println("your bag can now hold 5 more things!");

            }

        }

    }

    public void interactoptions (building room, player p1, int userinput, Scanner input) {
        if (room.getItems()[userinput].getType() == 1) { // if the selected item is an interactable (type 1), then...

            if (room.getItems()[userinput].getInterType().equals("foodstuffs")) { // if the selected interactable is a foodstuffs item, then...

                int put = userinput;// saves the index of the specified foodstuffs item

                foodoptions(userinput, p1, room, input);

                room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player ate it)

            } else { // the item is a piece of armor

                int put = userinput;

                String effect = room.getItems()[userinput].getInterEff();

                System.out.println("what will you do?\n[0 + enter] put it on\n[1 + enter] put it in your bag");

                userinput = input.nextInt();

                if (userinput == 0) { // if the player wants to put it on, then...

                    if (effect.substring(1, 2).equals("1")) { // if the armor only adds one durability point, then...

                        p1.setMhealth(p1.getMhealth() + 1);

                        System.out.println("your max health was increased by 1");

                    } else if (effect.substring(1, 2).equals("2")) { // if the armor adds two durability points, then...

                        p1.setMhealth(p1.getMhealth() + 2);

                        System.out.println("your max health was increased by 2");

                    } else if (effect.substring(1, 2).equals("3")) { // if the armor adds three durability points, then...

                        p1.setMhealth(p1.getMhealth() + 3);

                        System.out.println("your max health was increased by 3");

                    } else {

                        System.out.println("you put it on, but nothing happened...");

                    }

                    room.getItems()[put] = null; // remove the item from the list, since the player is now wearing it

                } else { // the player wants to put it in their bag, so...

                    int tracker = 0; // sees how many times specified item has been added to the bag

                    for (int i = 0; i < p1.getBagSpace(); i++) { // loop through the players bag, look for open spots

                        if (p1.getBag()[i] == null && tracker == 0) { // if this spot is open and the item hasnt been added yet, then...
                            p1.getBag()[i] = room.getItems()[put]; // put the specified item into that spot

                            tracker++; // increment the tracker to let the program know the item is in the bag now

                            System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                            room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag)
                        }

                    }

                    if (tracker == 0) { // if the item hasnt been added after checking for empty spaces, then...
                        System.out.println("it looks like your bag is full. throw something away?\n[0 + enter] yeah, throw something away\n[1 + enter] nah, keep my stuff");

                        userinput = input.nextInt();

                        if (userinput == 0) { // if the player wants to throw away an item, then...
                            //first print out the list of items in the bag
                            p1.printBag();

                            System.out.print("[number of the item you want to throw away + enter]:  ");

                            userinput = input.nextInt();

                            System.out.println(p1.getBag()[userinput].getName() + " was discarded");

                            p1.getBag()[userinput] = null; // remove the item in the specified spot

                            p1.getBag()[userinput] = room.getItems()[put]; // put the item in the emptied spot

                            System.out.println(room.getItems()[put].getName() + " has been added to your bag");

                            room.getItems()[put] = null; // remove that item from the list of interactables in the building (the player put it in their bag

                        } else { // the player wants to discard the item, not keep it
                            System.out.println(room.getItems()[put].getName() + " was discarded");
                        }
                    }
                }
            }
        }
    }

    public void save(player p1 ,area world, boolean r1, boolean r2, building room1, building room2, item npc1, item npc2, item npc3, item npc4, item obj1, item obj2, item obj3, item obj4, String user, Scanner input, int day, String time) {


        try {
            PrintWriter savewriter = new PrintWriter("save//save_01.txt");
            BufferedReader savereader = new BufferedReader(new FileReader("save//save_01.txt"));

            String precode = savereader.readLine();

            if (precode == null) { // if the save file hasnt been written to yet, then...
                System.out.println("saving...");

                // run through the checking system and create a string to write to the file.
                String savecode;

                // record the area
                savecode = world.getNumCode();

                // record the building
                if (r1) {
                    savecode += room1.getNumCode() + "-";
                } else if (r2) {
                    savecode += room2.getNumCode() + "-";
                } else {
                    savecode += "0-";
                }

                // record player stats

                // player health
                savecode += p1.getHP().toString();

                // player max health
                savecode += "/" + p1.getMhealth().toString();

                // player fatigue
                savecode += ":" + p1.getFat().toString();

                // player max fatigue
                savecode += "/5";

                // player items list

                //first create an array to document how much of each item the player has
                String[] allItems = new String[3];

                Integer applepie = 0;
                Integer ironarmor = 0;
                Integer comfyblanket = 0;
                Integer fabric = 0;

                allItems[1] = comfyblanket.toString();
                //next, create a for loop to run through the players bag and check how much of each item there is, and document it in the allItems[]
                for (int i = 0; i < p1.getBagSpace(); i++) {

                    if (p1.getBag()[i] != null) {
                        if (p1.getBag()[i].getName().equals("apple pie")) {
                            applepie++;
                        } else if (p1.getBag()[i].getName().equals("comfy blanket")) {
                            comfyblanket++;
                        } else if (p1.getBag()[i].getName().equals("iron armor")) {
                            ironarmor++;
                        } else if (p1.getBag()[i].getName().equals("fabric")) {
                            fabric++;
                        } else {
                            //do nothing
                        }
                    }

                }

                savecode += "{" + applepie.toString() + comfyblanket.toString() + ironarmor.toString() + fabric.toString() + "}";

                // npcs the player has talked to

                Integer one = 0;
                Integer two = 0;
                Integer three = 0;
                Integer four = 0;

                if (npc1.hasTalked()) {
                    one++;
                }

                if (npc2.hasTalked()) {
                    two++;
                }

                if (npc3.hasTalked()) {
                    three++;
                }

                if (npc4.hasTalked()) {
                    four++;
                }

                savecode += "{" + one.toString() + two.toString() + three.toString() + four.toString() + "}";

                // player quests
                if (p1.getQuests()[0] != null) {

                    savecode += "-1";

                } else {

                    savecode += "-0";

                }


                if (world.getQuests()[0].isComplete()) {

                    savecode += "1";

                } else {

                    savecode += "0";

                }

                if (p1.getQuests()[1] != null) {

                    savecode += "-1";

                } else {

                    savecode += "-0";

                }

                if (world.getQuests()[1].isComplete()) {

                    savecode += "1";

                } else {

                    savecode += "0";

                }

                // add blocks for more quests as needed

                // time and date

                savecode += "{" + day;

                savecode += p1.getActions() + "}";

                //write the new save code to the save file
                System.out.println("saved!");

                savewriter.write(savecode);

                savewriter.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean cards(player p1, item npc, Scanner scan) {

        /* cards()
         * this is the CARDS minigame. It'll be a simple number game that'll be played when the player has to battle someone
         * it uses random number generators and stuff
         * itll return if the player won or not
         */

        p1.setHP(3);

        boolean valid = false;

        boolean win = false;

        while (valid == false) {

            System.out.println("Welcome to CARDS!");

            System.out.println("would you like to know how the game is played?\n\n[0 + enter] yeah!\n[1 + enter] nah");

            int cardecision = scan.nextInt();

            if (cardecision == 0) {

                // explain the rules to the player

                String user;

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("minigamerules\\cards.txt"));

                    String text = reader.readLine(); // contains text from the dialogue.txt

                    while (text != null) { // if there is dialogue in this spot, then

                        System.out.println(text);

                        user = scan.next();

                        text = reader.readLine();

                    }

                    reader.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                valid = true;
            } else if (cardecision == 1) {

                System.out.println("alright. Good luck!\n");

                valid = true;
            } else {

                System.out.println("please enter a valid option");

            }
        }

        int npchp = 3;

        while (npchp > 0 && p1.getHP() > 0) {

            // player cards
            int pcard1 = (int)(Math.random() * (10 - 5) + 5);
            int pcard2 = (int)(Math.random() * (10 - 5) + 5);
            int pcard3 = (int)(Math.random() * (10 - 5) + 5);

            // npc selects a card

            int npcselection = (int)(Math.random() * (18 - 10) + 10);

            System.out.println(npc.getName() + "'s card: " + npcselection + "\n");

            // player card selection

            // print out the players cards

            System.out.println("card 1: " + pcard1 + " card 2: " + pcard2 + " card 3: " + pcard3);


            // selection

            boolean pc1selected = false;
            boolean pc2selected = false;
            boolean pc3selected = false;

            int sum = 0;
            int pinpt1 = 0;
            int pinpt2 = 0;

            for (int i = 1; i >= 0; i--) {

                int selected = 1;

                while (selected > 0) {

                    if (i == 1) {
                        System.out.print("\nenter the number of the first card you want to select: ");

                        int pselection = scan.nextInt();


                        if (pselection == 1 && pc1selected == false) {

                            pinpt1 = pcard1;

                            pc1selected = true;

                            selected--;

                        } else if (pselection == 2 && pc2selected == false) {

                            pinpt1 = pcard2;

                            pc2selected = true;

                            selected--;

                        } else if (pselection == 3 && pc3selected == false) {

                            pinpt1 = pcard3;

                            pc3selected = true;

                            selected--;

                        } else {

                            System.out.println("you have to select a different card");

                        }

                    } else {

                        System.out.print("\nenter the number of the second card you want to select: ");

                        int pselection = scan.nextInt();


                        if (pselection == 1 && pc1selected == false) {

                            pinpt2 = pcard1;

                            pc1selected = true;

                            selected--;

                        } else if (pselection == 2 && pc2selected == false) {

                            pinpt2 = pcard2;

                            pc2selected = true;

                            selected--;

                        } else if (pselection == 3 && pc3selected == false) {

                            pinpt2 = pcard3;

                            pc3selected = true;

                            selected--;

                        } else {

                            System.out.println("you have to select a different card");

                        }
                    }
                }




            }

            // the calculation

            sum = pinpt1 + pinpt2;

            if (sum > npcselection) { // if the sum of the cards the player selected is greater than the npc card

                npchp--;

                System.out.println(npc.getName() + " lost 1 hp! they have " + npchp + " hp left!");

            } else if (sum < npcselection) { // if the sum of the cards the player selected is less than the npc card

                p1.setHP(p1.getHP() - 1);

                System.out.println("you lost 1 hp! you have " + p1.getHP() + " hp left!");

            } else { // if the sum of the cards the player selected is equal to the npc card

                System.out.println("you guys tied, so nothing happened...");

            }

        }

        // check for wins or losses

        if (npchp == 0) {

            System.out.println("you won! congrats!");

            win = true;

        } else {

            System.out.println("you lost... sorry");

            win = false;

        }

        return win;

    }


}
