public class Player {
    private int lives;
    
    public Player() {
      this.lives = 5;
    }
    
    public Player(int live) {
      this.lives = live;
    }
    
    public int getLives() {
      return this.lives;
    }
    
    public void death() {
      this.lives--;
    }
  }
