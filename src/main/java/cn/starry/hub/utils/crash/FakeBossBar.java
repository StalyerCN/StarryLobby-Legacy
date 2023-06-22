package cn.starry.hub.utils.crash;

public interface FakeBossBar {
    public void setName(String var1);

    public void destroy();

    public void send();

    public float getProgress();

    public boolean isSend();

    public void setProgress(float var1);
}
