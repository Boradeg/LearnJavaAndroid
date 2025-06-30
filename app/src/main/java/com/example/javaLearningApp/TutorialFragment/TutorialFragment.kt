package com.example.javaLearningApp.TutorialFragment

import ItemAdapterTutorial
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.javaLearningApp.databinding.FragmentTutorialBinding

class TutorialFragment : Fragment() {

    private var mList: List<DataModalTutorial>? = null
    private var adapter: ItemAdapterTutorial? = null

    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        Log.d("result","onCreateView called tutorialFragment")




        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.setLayoutManager(LinearLayoutManager(requireContext()))
        //setDrawable()


        mList = ArrayList<DataModalTutorial>()

        //list1

        //list1
        val nestedList1: MutableList<String> = ArrayList()
        nestedList1.add("Pune-Satara Road Widening")
        nestedList1.add("Nipani-Chikodi Road Widening")
        nestedList1.add("Daund - Bhigwan Line Survey")
        nestedList1.add("Chyawanprash and Health Foods")


        val nestedList2: MutableList<String> = ArrayList()
        nestedList2.add("Book")
        nestedList2.add("Pen")
        nestedList2.add("Office Chair")
        nestedList2.add("Pencil")
        nestedList2.add("Eraser")
        nestedList2.add("NoteBook")
        nestedList2.add("Map")
        nestedList2.add("Office Table")

        val nestedList3: MutableList<String> = ArrayList()
        nestedList3.add("Decorates")
        nestedList3.add("Tea Table")
        nestedList3.add("Wall Paint")
        nestedList3.add("Furniture")
        nestedList3.add("Bedsits")
        nestedList3.add("Tea Table")
        nestedList3.add("Wall Paint")
        nestedList3.add("Furniture")
        nestedList3.add("Bedsits")
        nestedList3.add("Certain")
        nestedList3.add("Namkeen and Snacks")
        nestedList3.add("Certain")
        nestedList3.add("Namkeen and Snacks")
        nestedList3.add("Honey and Spreads")

        val items: MutableList<String> = ArrayList()
        items.add("Introduction")
        items.add("Drone Survey")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")
        items.add("MMRDA Daund")

        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList1, items[0]))
        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList2, items[1]))
        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList3, items[2]))
        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList3, items[3]))
        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList3, items[4]))
        (mList as ArrayList<DataModalTutorial>).add(DataModalTutorial(nestedList3, items[5]))



        adapter = ItemAdapterTutorial(mList as ArrayList<DataModalTutorial>)
        binding.recyclerView.setAdapter(adapter)



        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("result","On Destroy view tutorialFragment")
        _binding = null // Avoid memory leaks
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        Log.d("result","onViewCreated called tutorialFragment")

    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        Log.d("result","onAttach called tutorialFragment")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("result","onCreate called tutorialFragment")

    }

    override fun onStart() {
        super.onStart()
        Log.d("result","onStart called tutorialFragment")

    }

    override fun onPause() {
        super.onPause()
        Log.d("result","onPause called tutorialFragment")

    }

    override fun onResume() {
        super.onResume()
        Log.d("result","onResume called tutorialFragment")

    }

    override fun onStop() {
        super.onStop()
        Log.d("result","onStop called tutorialFragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("result","onDestroy called tutorialFragment")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("result","onDetach called tutorialFragment")

    }


}