package valenzuela.carlos.digimind.ui.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import valenzuela.carlos.digimind.R
import valenzuela.carlos.digimind.databinding.FragmentReminderBinding
import valenzuela.carlos.digimind.ui.Task
import valenzuela.carlos.digimind.ui.board.BoardFragment

class ReminderFragment : Fragment() {

    private lateinit var dashboardViewModel: ReminderViewModel
    private var _binding: FragmentReminderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(ReminderViewModel::class.java)

        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnSave: Button = root.findViewById(R.id.buttonDone)
        val tittle: EditText = root.findViewById(R.id.name)
        val sunday: CheckBox = root.findViewById(R.id.sunday)
        val monday: CheckBox = root.findViewById(R.id.monday)
        val tuesday: CheckBox = root.findViewById(R.id.tuesday)
        val wednesday: CheckBox = root.findViewById(R.id.wednesday)
        val thursday: CheckBox = root.findViewById(R.id.thursday)
        val friday: CheckBox = root.findViewById(R.id.friday)
        val saturday: CheckBox = root.findViewById(R.id.saturday)
        val time: EditText = root.findViewById(R.id.timeInput)

        btnSave.setOnClickListener {

            var title = tittle.text.toString()

            var days = ArrayList<String>()

            if(monday.isChecked){
                days.add("Monday")
            }
            if(tuesday.isChecked){
                days.add("Tuesday")
            }
            if(wednesday.isChecked){
                days.add("Wednesday")
            }
            if(thursday.isChecked){
            days.add("Thursday")
            }
            if(friday.isChecked){
                days.add("Friday")
            }
            if(saturday.isChecked){
                days.add("Satuday")
            }
            if(sunday.isChecked){
                days.add("Sunday")
            }

            var time = time.text.toString()

            var task = Task(title, days, time)

            BoardFragment.tasks.add(task)

            Toast.makeText(root.context, "New Task Added", Toast.LENGTH_LONG).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}