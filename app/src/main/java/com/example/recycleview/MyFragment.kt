package com.example.recycleview

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview.recyclerView.MyRecyclerViewAdapter
import com.example.recycleview.viewModels.MyViewModel

class MyFragment : Fragment() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // inflate layout of fragment
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind recyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // assign layoutManager (linear)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Adapter
        viewModel.dataList.observe(viewLifecycleOwner) { dataList ->
            // Update the adapter with the new data list
            recyclerView.adapter = MyRecyclerViewAdapter(dataList,
                onItemShownCallback = { position ->
                    // TODO: Call back methodes
                    Handler(requireActivity().mainLooper).post {
                        Toast.makeText(context, "Item $position has been seen for 2 sec", Toast.LENGTH_SHORT).show()
                    }
                },
                onClickAddListen = {position ->
                    // Store the current scroll position
                    val currentScrollPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    viewModel.toggleListener(position)
                    recyclerView.scrollToPosition(currentScrollPosition) // scroll back
                })
        }

        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {

            // Store the current scroll position
            val currentScrollPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            viewModel.clickButton()
            recyclerView.scrollToPosition(currentScrollPosition) // scroll back
            Toast.makeText(requireContext(), "New Item added", Toast.LENGTH_SHORT).show()
        }
    }

}