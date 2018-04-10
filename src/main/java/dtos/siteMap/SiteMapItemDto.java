package dtos.siteMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Payam Mostafaei
 * Creation time: 2017/May/21 - 11:51 AM
 */
public class SiteMapItemDto {

    private String title;
    private String icon;
    private String permission;
    private String mainView;
    private String url;
    private String templateUrl;
    private String controller;
    private List<String> files;
    private List<SiteMapItemDto> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMainView() {
        return mainView;
    }

    public void setMainView(String mainView) {
        this.mainView = mainView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public void addFile(String filePath) {
        if (this.files == null)
            this.files = new ArrayList<>();
        this.files.add(filePath);
    }

    public List<SiteMapItemDto> getChildren() {
        return children;
    }

    public void setChildren(List<SiteMapItemDto> children) {
        this.children = children;
    }

    public void addChild(SiteMapItemDto child) {
        if (this.children == null)
            this.children = new ArrayList<>();
        this.children.add(child);
    }
}
