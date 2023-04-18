package com.tahaproject.todoy_app.ui.todo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaproject.todoy_app.data.apiManger.personalTodo.PersonalTodoApi
import com.tahaproject.todoy_app.data.apiManger.teamTodo.TeamTodoApi
import com.tahaproject.todoy_app.data.models.requests.UpdateTodoTask
import com.tahaproject.todoy_app.data.models.responses.todosListResponse.Todo
import com.tahaproject.todoy_app.databinding.FragmentDetailsBinding
import com.tahaproject.todoy_app.ui.base.BaseFragment
import com.tahaproject.todoy_app.ui.todo.details.presenter.IDetailsContract
import com.tahaproject.todoy_app.ui.todo.details.presenter.IDetailsPresenter
import com.tahaproject.todoy_app.util.Constants
import com.tahaproject.todoy_app.util.SharedPreferenceUtil
import com.tahaproject.todoy_app.util.showToast
import okio.IOException

 class DetailsTodoFragment :
     BaseFragment<FragmentDetailsBinding,IDetailsPresenter>()
    ,IDetailsContract.View
 {
     private val sharedPreferenceUtil by lazy {
         SharedPreferenceUtil(requireContext())
     }
     override val presenter: IDetailsPresenter
         get() = IDetailsPresenter(this,
             PersonalTodoApi(sharedPreferenceUtil.getToken()),
             TeamTodoApi(sharedPreferenceUtil.getToken()))
     override val bindingInflate: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasKDetails = arguments?.
        getParcelable(Constants.TASK_DETAILS) as Todo?
        var updateTodoTask = UpdateTodoTask(tasKDetails!!.id,tasKDetails.status)
        viewDetails(tasKDetails)
        addCallBacks(updateTodoTask)
    }

    private fun addCallBacks(updateTodoTask: UpdateTodoTask) {
    onClickBack()
        onClickButton(updateTodoTask)
    }
     private fun onClickBack(){
         binding.appBarDetails.setNavigationOnClickListener {
             activity?.onBackPressed()
         }
     }
     private fun onClickButton( updateTodoTask: UpdateTodoTask){
         if (updateTodoTask.status==0){
             updateTodoTask.status =1
             binding.button.setOnClickListener {
                 presenter.updateTeamTodoTask(updateTodoTask)
                 binding.button.text = "Done"
                 binding.textViewTaskStats.text="in progress"
             }
         }
         else{
             updateTodoTask.status =2
             binding.button.setOnClickListener {
                 presenter.updateTeamTodoTask(updateTodoTask)
                 binding.button.visibility = View.GONE
                 binding.textViewTaskStats.text="Done"
         }

     }
     }
     private fun viewDetails(tasKDetails: Todo?){
         binding.textViewTaskTitle.text = tasKDetails?.title
         binding.textViewTaskDescription.text = tasKDetails?.description
         when(tasKDetails?.status){
             0->
             {binding.textViewTaskStats.text = "Todo"
                    binding.button.text="Start Task"}
             1->
             { binding.textViewTaskStats.text = "In progress"
                 binding.button.text="done"}
             2->
             {binding.textViewTaskStats.text = "Done"
                 binding.button.visibility= View.GONE}
         }
         if(tasKDetails?.assignee=="")
             binding.chipMemberName.visibility= View.GONE
         else
             binding.chipMemberName.text = tasKDetails?.assignee

     }
     fun newInstance(tasKDetails: Todo) =
        DetailsTodoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Constants.TASK_DETAILS ,tasKDetails)
            }
        }


    override fun showTaskUpdated(successMessage: String) {
        showToast(successMessage)
    }

    override fun showError(error: IOException) {
        showToast(error)
    }
 }
