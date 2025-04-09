public class Shot
{
    private int nummer;
    private double score;
    private int x, y;

    public Shot(int nummer, double score, int x, int y)
    {
        this.nummer = nummer;
        this.score = score;
        this.x = x;
        this.y = y;
    }

        public int getNummer()
        {
            return nummer;
        }

        public double getScore()
        {
            return score;
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }
    }