import com.mysql.cj.x.protobuf.Mysqlx;
import com.scccy.untils.OkHttpClientUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class test {

    @Test
    public void test01() throws IOException {
        String url ="http://v3-webc.douyinvod.com/c85284b295c2df5ee0f7fb380c23f9f4/664de66f/video/tos/cn/tos-cn-ve-15/o8AQafCMlD0yzm1DetdBUzACgNhRAUjEazw9IB/?a=6383&ch=11&cr=3&dr=0&lr=all&cd=0%7C0%7C0%7C3&cv=1&br=2018&bt=2018&cs=0&ds=4&ft=GTeMyRhuffPdr5~-vv2NvAq-antLjrKQ3gj.Rka7zdgpUjVhWL6&mime_type=video_mp4&qs=0&rc=NGc2NzxlOjw5NzZpZjQ2OEBpanRuOjU6Zjk8cjMzNGkzM0AxMi4uYzRiXjAxLy8tMjQ1YSMtMWNucjRfMmFgLS1kLWFzcw%3D%3D&btag=c0000e00008000&cquery=100B_100x_100z_100o_101n&dy_q=1716374081&feature_id=46a7bb47b4fd1280f3d3825bf2b29388&l=20240522183441E6F1E689322EFD0C2745";
        OkHttpClientUtil.downloadFile(url,"/home/project/DownLoadVideo/video");

    }
}
