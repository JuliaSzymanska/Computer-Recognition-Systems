package tech.szymanskazdrzalik.fuzzy.dao;

import tech.szymanskazdrzalik.fuzzy.gui.PodsumowanieLingwistyczneIMiary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

public class FilePodsumowanieLingwistyczneIMiarySaveDAO implements PodsumowanieLingwistyczneIMiarySaveDAO {
    private final String fileName;

    private DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHH");

    public FilePodsumowanieLingwistyczneIMiarySaveDAO(String filename) {
        this.fileName = filename;
    }

    @Override
    public void Save(PodsumowanieLingwistyczneIMiary podsumowanieLingwistyczneIMiary) {
        try (
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(fileName + "_" + timeStampPattern.format(java.time.LocalDateTime.now()) + ".txt", true), StandardCharsets.UTF_8);
        ) {
            writer.append(podsumowanieLingwistyczneIMiary.toString());
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
