package labtest1;

import com.sun.xml.internal.ws.util.StringUtils;
import static java.awt.SystemColor.text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asgn_21 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        FileInputStream in = new FileInputStream("lab.c");
        PrintWriter out = new PrintWriter(new FileWriter("out1.txt"));

        String a = "", text = "";
        int c;

        while ((c = in.read()) != -1) {
            a = a + (char) c;
        }

        int l = a.length();

        for (int i = 0; i < l; i++) {
            if (a.charAt(i) == '"') {
                i++;
                while (a.charAt(i) != '"') {
                    i++;
                }
            }
            if (a.charAt(i) == '/' && a.charAt(i + 1) == '*') {
                i += 2;
                while (!(a.charAt(i) == '*' && a.charAt(i + 1) == '/')) {
                    i++;
                }
                i += 1;
            } else if (a.charAt(i) == '/' && a.charAt(i + 1) == '/') {
                i += 2;
                while (a.charAt(i) != '\n') {
                    i++;
                }
            } else {
                if (a.charAt(i) == '(' || a.charAt(i) == ')' || a.charAt(i) == ',' || a.charAt(i) == ';' || a.charAt(i) == ':' )
                    text = text + ' ';
                text = text + a.charAt(i);
            }
        }

        Pattern p = Pattern.compile("(alignas |alignof |and |and_eq |asm |auto |bitand |bitor |bool |break |case |catch |char |"
                + "char16_t |char32_t |class |compl |concept |const |constexpr |const_cast |continue |decltype |default |"
                + "delete |do |double |dynamic_cast |else |enum |explicit |export |extern |false |float |for |friend |goto |"
                + "if |inline |int |long |mutable |namespace |new |noexcept |not |not_eq |nullptr |operator |or |or_eq |private |"
                + "protected |public |register |reinterpret_cast |requires |return |short |signed |sizeof |static |static_assert "
                + "|static_cast |struct |switch |template |this |thread_local |throw |true |try |typedef |typeid |typename |union "
                + "|unsigned |using |virtual |void |volatile |wchar_t |while |xor |xor_eq )");
        Matcher m = p.matcher(text);

        String ss = "";
        String hash[] = new String[100];
        int count[] = new int[100];
        int start = 0, k = 0, i;

        while (m.find(start)) {
            ss = m.group(1).toString();
            for (i = 0; i < k; i++) {
                if (ss.matches(hash[i])) {
                    count[i]++;
                    break;
                }
            }
            if (i == k) {
                hash[i] = ss;
                count[i]++;
                k++;
            }

            //System.out.println(ss);
            start = m.end();
            //out.println(ss);
            ss = "";
        }

        for (i = 0; i < k; i++) {
            out.print(hash[i]);
            out.print(' ');
            out.println(count[i]);
        }

        in.close();
        out.close();
    }
}