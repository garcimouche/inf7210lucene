package inf7210

import org.apache.lucene.analysis.en.EnglishAnalyzer
import org.apache.lucene.document.*
import org.apache.lucene.index.*
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.TermQuery
import org.apache.lucene.store.SimpleFSDirectory
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {

    val movies = loadMovies()

    val fs = SimpleFSDirectory.open(Paths.get("index","movie"))

    fs.use {
        val indexWriter = IndexWriter(fs, IndexWriterConfig(EnglishAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE))

        indexWriter.use {

            movies.values.forEach {
                val document = Document()
                document.add(StringField("id", it.id.trim(), Field.Store.YES))
                document.add(TextField("title", it.title.trim(), Field.Store.YES))
                it.actors.forEach {
                    if(it.character.trim()!= "")
                        document.add(TextField("character", it.character.trim(), Field.Store.YES))
                    if(it.name.trim()!= "")
                    document.add(TextField("actor", it.name.trim(), Field.Store.YES))
                }
                if(it.plot.summary.trim()!= "")
                    document.add(TextField("summary", it.plot.summary.trim(), Field.Store.YES))
                indexWriter.addDocument(document)
            }

            indexWriter.flush()
            indexWriter.commit()
        }
    }

}

data class Actor(val character: String, val name:String);
data class Plot(val summary: String);
class Movie(val id: String, val title:String){
    var actors:MutableList<Actor> = mutableListOf()
    var plot: Plot = Plot("")
}

fun loadMovies(): Map<String, Movie> {
    val movies = mutableMapOf<String, Movie>()
    //store movies
    File(Paths.get("datasource","movie.metadata.tsv").toUri()).forEachLine {
        val movie = it.split("\t")
        movies[movie[0]] = Movie(movie[0], movie[2])
    }

    //store actors
    File(Paths.get("datasource", "character.metadata.tsv").toUri()).forEachLine {
        val wActor = it.split("\t")
        val actor = Actor(character = wActor[3], name = wActor[8])
        movies[wActor[0]]?.actors?.add(actor)
    }

    //store plots
    File(Paths.get("datasource", "plot_summaries.txt").toUri()).forEachLine {
        val plot = it.split("\t")
        movies[plot[0]]?.plot = Plot(plot[1])
    }
    return movies
}
