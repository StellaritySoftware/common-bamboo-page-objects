package helpers

class VersionComparator {

    public static int compare(String version1, String version2){

        String[] arr1 = version1.split ("\\.");
        String[] arr2 = version2.split("\\.");

        for(int i = 0; i < Math.min( arr1.length, arr2.length); i++){
            int a = Integer.parseInt(arr1[i]);
            int b = Integer.parseInt(arr2[i]);

            if(a > b){

                return 1;
            }

            if(a < b){

                return -1;
            }
        }
        return  0;
    }
}