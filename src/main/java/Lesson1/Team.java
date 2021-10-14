package Lesson1;

public class Team {

    private static Course course;
    private String name = "Galileo";
    private String memberName;
    private int vRun;
    private int vSwim;
    private int vCrawl;
    private int stamina;


    Team(int stamina, String memberName) {
        this.memberName = memberName;
        this.stamina = stamina;
    }

    public static void main(String[] args) {

        Team[] Galileo = {
                new Team(250, "Josh"),
                new Team(500, "Steve"),
                new Team( 150, "Debora"),
                new Team( 100, "Clay"),
        };

        Course[] Ivent = {
                new Course(100, "trass"),
                new Course(200, "pool"),
                new Course(150, "lane"),
        };

    teamInfo(Galileo);
        System.out.println();
    course.doIt(Galileo, Ivent);

    }
// Так оно покрасивее будет
    public static void teamInfo(Team[] Galileo){
        for (int i = 0; i < Galileo.length; i++){
            System.out.println(i + 1 + ") Участник " + Galileo[i].getMemberName() + ", выносливость - " + Galileo[i].getStamina());
        }
    }

    public int getStamina() {
        return stamina;
    }

    public String getMemberName() {
        return memberName;
    }
}
