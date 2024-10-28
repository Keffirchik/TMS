import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R

class AdapterClass(private val noteList: MutableList<Note>, val callback : (id : Int) -> Unit) :
    RecyclerView.Adapter<AdapterClass.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = this.itemView.findViewById(R.id.titleTextView)
        val contentText: TextView = this.itemView.findViewById(R.id.contentTextView)
        val dateText: TextView = this.itemView.findViewById(R.id.dateTextView)
        val delete: TextView = this.itemView.findViewById(R.id.ly_delete_button_ni)
    }

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
//        noteList.removeAt(index)
        callback.invoke(index)
        notifyDataSetChanged()
//        notifyItemRemoved(index)
    }

}