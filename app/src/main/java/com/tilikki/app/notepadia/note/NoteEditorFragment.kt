package com.tilikki.app.notepadia.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tilikki.app.notepadia.databinding.NoteEditorFragmentBinding
import com.tilikki.app.notepadia.models.Note

class NoteEditorFragment : Fragment() {

  companion object {
    const val NOTE_TO_EDIT = "NOTE_TO_EDIT"

    fun newInstance(note: Note): NoteEditorFragment {
      return NoteEditorFragment().apply {
        arguments = Bundle().apply {
          putParcelable(NOTE_TO_EDIT, note)
        }
      }
    }
  }

  private lateinit var binding: NoteEditorFragmentBinding
  private val viewModel: NoteEditorViewModel by activityViewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View {
    binding = NoteEditorFragmentBinding.inflate(inflater, container, false)
    binding.noteEditorViewModel = viewModel

    viewModel.note.observe(viewLifecycleOwner) { vmNote ->
      Toast.makeText(context, "${vmNote.title}: ${vmNote.content}", Toast.LENGTH_SHORT).show()
    }

    //    viewModel.title.observe(viewLifecycleOwner) { title ->
    //      binding.etNoteTitle.setText(title, TextView.BufferType.EDITABLE)
    //    }
    //
    //    viewModel.content.observe(viewLifecycleOwner) { content ->
    //      binding.etNoteContents.setText(content, TextView.BufferType.EDITABLE)
    //    }

    return binding.root
  }

}