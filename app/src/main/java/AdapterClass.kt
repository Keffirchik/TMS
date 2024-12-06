import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tms.R
import com.example.tms.presentation.model.NotesType

class AdapterClass(
    private val listOfItems: MutableList<NotesType>?,
    private val callback: (id: Int, command: String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val infoBlock: TextView = this.itemView.findViewById(R.id.InfoBlockView)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = this.itemView.findViewById(R.id.titleTextView)
        val contentText: TextView = this.itemView.findViewById(R.id.contentTextView)
        val dateText: TextView = this.itemView.findViewById(R.id.dateTextView)
        val delete: TextView = this.itemView.findViewById(R.id.ly_delete_button_ni)
        val share: TextView = this.itemView.findViewById(R.id.ly_share_ni)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            AdapterType.INFO_TYPE.ordinal -> InfoViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.recycler_view_info_item, parent, false)
                )
            AdapterType.NOTE_TYPE.ordinal -> NoteViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_item, parent, false)
            )

            else -> throw IllegalArgumentException("No such type")
        }
    }

    override fun getItemCount(): Int {
        return listOfItems?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = listOfItems?.get(position)) {
            is NotesType.InfoBlock -> (holder as InfoViewHolder).apply {
                infoBlock.text = item.info
            }
            is NotesType.Note -> (holder as NoteViewHolder).apply {
                titleText.text = item.title
                contentText.text = item.content
                dateText.text = item.date
                delete.setOnClickListener {
                    deleteNote(position)
                }
                share.setOnClickListener {
                    shareNote(position)
                }
            }

            null -> TODO()
        }
    }

    override fun getItemViewType(position: Int): Int = when (listOfItems?.get(position)) {
        is NotesType.InfoBlock -> AdapterType.INFO_TYPE.ordinal
        is NotesType.Note -> AdapterType.NOTE_TYPE.ordinal
        null -> TODO()
    }

    private fun shareNote(position: Int) {
        callback.invoke(position, "share")
    }

    private fun deleteNote(position: Int) {
        callback.invoke(position, "delete")
        notifyItemRemoved(position)
    }

    private enum class AdapterType {
        INFO_TYPE, NOTE_TYPE
    }
}