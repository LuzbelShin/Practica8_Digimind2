package valenzuela.carlos.digimind.ui.board

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import valenzuela.carlos.digimind.R
import valenzuela.carlos.digimind.databinding.FragmentBoardBinding
import valenzuela.carlos.digimind.ui.Task


class BoardFragment : Fragment() {



    private var adapter: taskAdapter? = null
    private lateinit var boardViewModel: BoardViewModel
    private var _binding: FragmentBoardBinding? = null

    companion object{
        var tasks = ArrayList<Task>()
        var firstTime = true
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        boardViewModel =
            ViewModelProvider(this).get(BoardViewModel::class.java)

        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(firstTime){
            fillTask()
            firstTime = false
        }

        adapter = taskAdapter(root.context, tasks)

        val gridView: GridView = root.findViewById(R.id.reminders) as GridView

        gridView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillTask(){
        tasks.add(Task("Practice1", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice2", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice3", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice4", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice5", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice6", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice7", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice8", arrayListOf("Monday", "Sunday"), "18:00"))
    }

    private class taskAdapter(context: Context, var tasks: ArrayList<Task>) : BaseAdapter() {
        var context: Context? = context

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = tasks[position]

            val inflator = LayoutInflater.from(context)
            val view = inflator.inflate(R.layout.task_view, null)

            val tvTittle:TextView = view.findViewById(R.id.tvTittle)
            val tvDays:TextView = view.findViewById(R.id.tvDays)
            val tvTime:TextView = view.findViewById(R.id.tvTime)

            tvTittle.text = (task.tittle)
            tvDays.text =(task.days.toString())
            tvTime.text = (task.time)

            return view
        }
    }
}