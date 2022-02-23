public class quest {
    private int mainQuest;

    private String name;

    private String description;

    private boolean state;

    public quest(String n, String desc, int mq) {
        mainQuest = mq;

        name = n;

        description = desc;

        state = false;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public int isMainQuest() {
        return mainQuest;
    }

    public boolean isComplete() {
        return state;
    }

    public void setstate(boolean b) {
        state = b;
    }
}
