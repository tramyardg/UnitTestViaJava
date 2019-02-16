/**
 * Singleton pattern
 */
public class PermitRepository {
    private static PermitRepository permitRepository = null;

    private PermitRepository() {

    }

    public static PermitRepository getInstance() {
        if (permitRepository == null) {
            permitRepository = new PermitRepository();
        }
        return permitRepository;
    }

    // irritating global dependency solution 1: static instance setter
    public static void setTestingIntance(PermitRepository repository) {
        permitRepository = repository;
    }

    // irritating global dependency solution 2: reset the singleton
    public static void resetSingleton() {
        permitRepository = null;
    }
}
