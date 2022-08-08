package com.project.todoslist.uis.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.todoslist.adapters.TodosViewAdapter
import com.project.todoslist.databinding.FragmentListItemBinding
import com.project.todoslist.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

const val TAG = "ListItemFragmentActivity"
class ListItemFragment : Fragment() {

    private lateinit var binding: FragmentListItemBinding
    private lateinit var todosViewAdapter: TodosViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListItemBinding.inflate(layoutInflater)

        lifecycleScope.launch(Dispatchers.Main) {
            binding.progressBar.isVisible = true
            try {
                val res = RetrofitInstance.api.getTodos()
                if (res.isSuccessful){
                    todosViewAdapter = TodosViewAdapter(res.body()!!)
                    setupRecyclerView()
                }

            } catch (e: IOException) {
                Log.e(TAG, "You might not have internet connection $e")
                binding.progressBar.isVisible = false
            } catch(e: HttpException) {
                Log.e(TAG, "Unexpected response $e")
                binding.progressBar.isVisible = false
            }

            /*if (res.){

            }*/
            binding.progressBar.isVisible = false

        }
        return binding.root
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {

        //todosViewAdapter = TodosViewAdapter(res as List<Todos>)
        adapter = todosViewAdapter
        layoutManager = LinearLayoutManager(this.context)
    }
}