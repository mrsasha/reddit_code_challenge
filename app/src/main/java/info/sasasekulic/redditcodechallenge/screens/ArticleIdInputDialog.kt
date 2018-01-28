package info.sasasekulic.redditcodechallenge.screens

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import info.sasasekulic.redditcodechallenge.R
import kotlinx.android.synthetic.main.dialog_input.view.*


class ArticleIdInputDialog : DialogFragment() {

    companion object {
        val TAG = "articleinput"
    }

    lateinit var listener: ArticleIdInputDialogListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = activity as ArticleIdInputDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement ArticleIdInputDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity as Context)
        val inflater = (activity as Activity).layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_input, null)
        builder.setView(dialogView)

        builder.setMessage(R.string.title_load_article)
                .setPositiveButton(R.string.action_load, DialogInterface.OnClickListener { dialog, id ->
                    listener.onLoadArticleClick(dialogView.article_id.text.toString())
                })
                .setNegativeButton(R.string.action_cancel, DialogInterface.OnClickListener { dialog, id ->
                    dismiss()
                })
        return builder.create()
    }

    interface ArticleIdInputDialogListener {
        fun onLoadArticleClick(articleId: String)
    }
}