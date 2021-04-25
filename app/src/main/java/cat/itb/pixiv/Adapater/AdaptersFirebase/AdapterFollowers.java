package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterFollowers extends FirebaseRecyclerAdapter<User, AdapterFollowers.ViewHolderFollowers> {

private User model;
private Context context;

public Context getContext() {
        return context;
        }
public void setContext(Context context) {
        this.context = context;
        }

public AdapterFollowers(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
        }

@Override
protected void onBindViewHolder(@NonNull ViewHolderFollowers holder, int position, @NonNull User model) {
        this.model = model;
        holder.bind();
        }

@NonNull
@Override
public ViewHolderFollowers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
        }

class ViewHolderFollowers extends RecyclerView.ViewHolder {

    CircleImageView imageViewFollowers;
    TextView textViewUsername;
    MaterialButton followButton;

    public ViewHolderFollowers(@NonNull View itemView) {
        super(itemView);


    }

    public void bind(){
        Picasso.with(getContext()).load(model.getImatgePerfil()).into(imageViewFollowers);
        textViewUsername.setText(model.getUsername());
        followButton = itemView.findViewById(R.id.followButton);


    }
}
}

