package com.shoppingmall.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service
class FileService {

    private val uploadDir = Paths.get("uploads")

    fun saveFile(file: MultipartFile): String {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir)
        }

        val uniqueFileName = UUID.randomUUID().toString() + "_" + file.originalFilename
        val targetPath: Path = uploadDir.resolve(uniqueFileName)

        file.transferTo(targetPath)

        return "/uploads/$uniqueFileName"
    }
}
