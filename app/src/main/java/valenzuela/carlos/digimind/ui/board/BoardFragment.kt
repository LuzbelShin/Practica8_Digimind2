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
    ): View? {
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

    fun fillTask(){
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
        tasks.add(Task("Practice", arrayListOf("Monday", "Sunday"), "18:00"))
    }

    private class taskAdapter: BaseAdapter{
        var tasks = ArrayList<Task>()
        var context: Context? = null

        constructor(context: Context, tasks: ArrayList<Task>){
            this.context = context
            this.tasks = tasks
        }

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
            var task = tasks[position]

            var inflator = LayoutInflater.from(context)
            var view = inflator.inflate(R.layout.task_view, null)

            var tvTittle:TextView = view.findViewById(R.id.tvTittle)
            var tvDays:TextView = view.findViewById(R.id.tvDays)
            var tvTime:TextView = view.findViewById(R.id.tvTime)

            tvTittle.setText(task.tittle)
            tvDays.setText(task.days.toString())
            tvTime.setText(task.time)

            return view
        }
    }
}