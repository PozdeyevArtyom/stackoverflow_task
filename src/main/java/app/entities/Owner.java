package app.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
    private int reputation;
    private int id;
    private String profileType;
    private String profilePicture;
    private String name;
    private String link;
    private String acceptRate;

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    @JsonGetter("user_id")
    public int getId() {
        return id;
    }

    @JsonSetter("user_id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("user_type")
    public String getProfileType() {
        return profileType;
    }

    @JsonSetter("user_type")
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    @JsonGetter("profile_image")
    public String getProfilePicture() {
        return profilePicture;
    }

    @JsonSetter("profile_image")
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @JsonGetter("display_name")
    public String getName() {
        return name;
    }

    @JsonSetter("display_name")
    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @JsonGetter("accept_rate")
    public String getAcceptRate() {
        return acceptRate;
    }

    @JsonSetter("accept_rate")
    public void setAcceptRate(String acceptRate) {
        this.acceptRate = acceptRate;
    }
}
