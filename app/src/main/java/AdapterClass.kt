import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R

class AdapterClass(private val noteList: MutableList<Note>) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = this.itemView.findViewById(R.id.titleTextView)
        val contentText: TextView = this.itemView.findViewById(R.id.contentTextView)
        val dateText: TextView = this.itemView.findViewById(R.id.dateTextView)
        val delete: TextView = this.itemView.findViewById(R.id.ly_delete_button_ni)
    }

    private val prefsName: String = "NotePrefs"
    private val keyNoteCount: String = "NoteCount"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.titleText.text = currentItem.title
        holder.contentText.text = currentItem.content
        holder.dateText.text = currentItem.noteDate
        holder.delete.setOnClickListener {
            deleteNote(position)
        }

    }

    private fun deleteNote(index: Int){
        noteList.removeAt(index)
//        saveNotesToPreferences()
        notifyDataSetChanged()
//        notifyItemRemoved(index)
    }

//    private fun saveNotesToPreferences() {
//        sharedPreferences = getSharedPreferences(prefsName, MODE_PRIVATE)
//        editor = sharedPreferences.edit()
//
//        editor.putInt(keyNoteCount, noteList.size)
//        for (i in 1..<noteList.size) {
//            val note = noteList[i]
//            editor.putString("note_title_$i", note.title)
//            editor.putString("note_content_$i", note.content)
//        }
//        editor.apply()
//    }

}