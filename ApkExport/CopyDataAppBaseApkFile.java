import java.io.IOException;

/**
 * @author: pandaren
 * @createTime: 2022/2/18
 * ����androidϵͳ�Ѱ�װapp�İ�װ��apk
 * Android 8.0 ��ʼ��������·����Ϊ/data/app/xxx.xx.xx-��hashֵ��ֱ��pull /data/app/xxx.xx.xx-��hashֵ��ʧ����ʾ��adb: error: failed to stat remote object '/mnt/data/app/xxx.xx.xx-��hashֵ': No such file or directory��
 * �����Ҫ���⴦��һ��
 * <p>
 * ���裺
 */
public class CopyDataAppBaseApkFile {

    public static class Imp {
        private String appFilename;

        public Imp(String appFileNameInFileExplorer) {
            this.appFilename = appFileNameInFileExplorer;
        }

        public void start() {
            //1��ͨ��appĿ¼��ʾ��apk�ļ�����ÿ��Ա�������ʵ���ļ���
            final String pullOutFilename = String.format(new String("%1$s%2$s"), this.appFilename, "==/base.apk");
            final Runtime runtime = Runtime.getRuntime();
            if (runtime != null) {
                try {
                    final Process process = runtime.exec(String.format("adb pull %1$s %2$s", pullOutFilename, String.format("%1$s.apk", this.appFilename.substring(this.appFilename.lastIndexOf("/") + 1, this.appFilename.lastIndexOf("-")))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args != null && args.length > 0) {
            new Imp(args[0]).start();
        }
    }
}
