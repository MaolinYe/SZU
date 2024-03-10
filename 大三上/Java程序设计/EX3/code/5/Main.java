import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "AboutSZU.txt";        // 读取文件路径
        Map<String, Integer> wordCountMap = new HashMap<>();        // 创建HashMap用于存储单词及其出现次数
        try {
            File file = new File(filePath);            // 打开文件
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {            // 逐行读取文件内容
                String line = scanner.nextLine();
                String[] words = line.split("[^a-zA-Z]+");    // 使用正则表达式分割行成单词数组
                for (String word : words) {                // 遍历单词数组，统计每个单词的出现次数
                    if (!word.isEmpty()) {
                        String lowercaseWord = word.toLowerCase();   // 如果是大写则转换为小写
                        // 更新单词出现次数
                        wordCountMap.put(lowercaseWord, wordCountMap.getOrDefault(lowercaseWord, 0) + 1);
                    }
                }
            }
            scanner.close();            // 关闭文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Map.Entry<String, Integer>> wordCountList = new ArrayList<>(wordCountMap.entrySet());
        wordCountList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));        // 根据出现次数从多到少排序单词
        int count = 0;        // 输出出现次数最多的50个英文单词
        for (Map.Entry<String, Integer> entry : wordCountList) {
            System.out.print(entry.getKey() + " ");
            count++;
            if (count % 10 == 0) {
                System.out.println();
            }
            if (count >= 50) {
                break;
            }
        }
    }
}
