class Solution {
    public String frequencySort(String s) {
        HashMap<String,Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(String temp: s.split("")){
            set.add(temp);
            if (map.containsKey(temp)){
                map.replace(temp, map.get(temp)+1);
            } else {
                map.put(temp,1);
            }
        }
        int maxValue = Collections.max(map.values());
        for(int i = maxValue; i >= 1; i--){
            for(String key : map.keySet()){
                if(map.get(key).equals(i)){
                    stringBuilder.append(key.repeat(i));
                }
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

}