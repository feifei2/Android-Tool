import java.io.IOException;

/**
 * @author: pandaren
 * @createTime: 2022/2/18
 * 拷贝android系统已安装app的安装包apk
 * Android 8.0 开始，完整的路径变为/data/app/xxx.xx.xx-类hash值。直接pull /data/app/xxx.xx.xx-类hash值会失败提示“adb: error: failed to stat remote object '/mnt/data/app/xxx.xx.xx-类hash值': No such file or directory”
 * 因此需要额外处理一下
 * <p>
 * 步骤：
 */
public class CopyDataAppBaseApkFile {

    public static class Imp {
        private String appFilename;

        public Imp(String appFileNameInFileExplorer) {
            this.appFilename = appFileNameInFileExplorer;
        }

        public void start() {
            //1、通过app目录显示的apk文件名获得可以被导出的实际文件名
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
