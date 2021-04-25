package cat.itb.pixiv.ClassesModels;

public class User {
    String username;
    String password;
    String key;
    String imatgePerfil;

    public User() {
    }

    public User(String username, String password, String key, String imatgePerfil) {
        this.username = username;
        this.password = password;
        this.key = key;
        this.imatgePerfil = imatgePerfil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImatgePerfil() {
        return imatgePerfil;
    }

    public void setImatgePerfil(String imatgePerfil) {
        this.imatgePerfil = imatgePerfil;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", imatgePerfil='" + imatgePerfil + '\'' +
                '}';
    }
}
