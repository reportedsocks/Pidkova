import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

class StringGenerationPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("stringGeneration", StringGenerationTask::class.java) {
            prefix = "generated_"
            group = "Custom"
            description = "Generates strings.xml based on input .csv file."
        }
    }
}

abstract class StringGenerationTask: DefaultTask() {

    @Input
    var source: String = "${project.projectDir}/strings.csv"

    @Input
    var output: String = "${project.projectDir}/src/main/res/values/strings.xml"

    @Input
    var prefix: String = ""

    @TaskAction
    fun action() {

        val sourceFile = File(source)
        if (!sourceFile.exists()) throw Exception("No source file")

        val text = StringBuilder()
        text.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n")
        text.append("<resources>\n")

        sourceFile.inputStream().bufferedReader().use {
            it.readLines().forEach { line ->
                val value = line.substringAfter(',')
                val name = line.substringBefore(',')
                text.append("<string name=\"$prefix$name\">$value</string>\n")
            }
        }
        text.append("</resources>\n")

        val file = File(output)
        if (file.exists()) {
            file.delete()
        }

        file.outputStream().bufferedWriter().use {
            it.write(text.toString())
        }
    }

}