
public class TryExitFinally {

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                // Non Arguments, The thread death error!
                throw new ThreadDeath();
            }
        });
        try {
            System.exit(0);
        } finally {
            System.out.println("In the finally block");
        }
    }
}
