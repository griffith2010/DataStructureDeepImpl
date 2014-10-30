    package wenyu.sample.DataStructuresAndAlgAnalysys;

    /**
     * Protocol for Hashable objects.
     * @author Wenyu Chang
     */
    public interface Hashable
    {
        /**
         * Compute a hash function for this object.
         * @param tableSize the hash table size.
         * @return (deterministically) a number between
         *     0 and tableSize-1, distributed equitably.
         */
        int hash( int tableSize );
    }
