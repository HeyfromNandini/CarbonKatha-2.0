package project.app.carbonkatha20.presentation.food

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroceryViewModel @Inject constructor() : ViewModel() {
    private val _selectedItems = mutableStateListOf<GroceryItem>()
    val selectedItems: List<GroceryItem> = _selectedItems

    fun addItem(item: GroceryItem) {
        _selectedItems.add(item)
    }

    fun saveList(items: List<DinnerItem>) {
        // Here you would implement saving to local storage or backend
        // For now, we'll just update the selected items
        _selectedItems.clear()
        items.forEach { dinnerItem ->
            _selectedItems.add(
                GroceryItem(
                    name = dinnerItem.name,
                    quantity = "${dinnerItem.quantity} ${dinnerItem.unit}",
                    image = dinnerItem.image
                )
            )
        }
    }
} 