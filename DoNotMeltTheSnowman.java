  public class DoNotMeltTheSnowman {

  public static void main(String[] args) {
    int x = Integer.parseInt(args[0]);
    Level[] levels = Levels.getLevels();
    Level level = levels[x];
    Board board = new Board(PieceUtils.charsToPieces( level.getCharArray(),
level.getWidth(), level.getHeight()));
    System.out.println("Welcome! Level " + x);

 loop: while (true) {
      Result result = board.fireLaser();
      board.renderBoard();
      switch(result) {
        case HIT_TARGET: {
         System.out.println("Congratulations! Level Up!");
         break loop;
        }
        case MELT_SNOWMAN: {
          System.out.println("Game Over!");
          break loop;
        }
        default : {
          System.out.println("X:");
           int ox = IOUtil.readInt();
          System.out.println("Y:");
           int oy = IOUtil.readInt();
          board.rotatePiece(new Coordinate(ox,oy));
          board.clearLasers();
        }
      }
    }

  }


}
