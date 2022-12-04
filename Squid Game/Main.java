public class Main {
    public static void main(String[] args) {
      Player play = new Player(10);
      Ladder ladder = new Ladder(5);
      int[][] laddercreate = ladder.createLadder();
      ladder.printLadderBlank();
      System.out.println("Total Lives: " + play.getLives());
      while (ladder.selections.size() < ladder.panels) {
        System.out.println("---------LEGEND-----------");
        System.out.println("0: Glass ");
        System.out.println("1: Tempered Glass ");
        System.out.println("--------------------------");
        if (play.getLives() == 0) {
          System.out.println("YOU DIED");
          System.exit(0);
        } 
        System.out.println("Current Place in Ladder: " + ladder.selections.size());
        if (!ladder.calcPanel(laddercreate, ladder.selections))
          play.death(); 
        System.out.println("Total Lives: " + play.getLives());
        System.out.println("-------------------------------------");
        ladder.printCurrentLadder();
      } 
      System.out.println("Congrats You Won!!");
      ladder.printLadder();
    }
  }
