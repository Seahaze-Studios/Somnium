package util;

import managers.ImageManager;
import managers.SoundManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import java.io.File;

public record Resource(File file) implements DeferredResource {
    public String getDescription() { return file.getName(); }
    public void load() {
        String path = file.getPath();

        // split[0] is filename, split[1] is file extension
        String[] split = file.getName().split("\\.");

        // Turning off deferred loading temporarily, as deferred loading with sounds causes issues
        LoadingList.setDeferredLoading(false);
        try {
            switch (split[1].toLowerCase()) {
                case "png":
                    ImageManager.Images.put(split[0], new Image(path));
                    break;
                case "wav":
                case "ogg":
                    SoundManager.Sounds.put(split[0], new Sound(path));
                    break;
                default:
                    System.out.println("File Type not Recognized");
                    break;
            }
        } catch(Exception e) {
            System.out.println("Failed to load file");
        } finally {
            LoadingList.setDeferredLoading(true);
        }
    }
}
