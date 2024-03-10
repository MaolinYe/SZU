P_dB=-10:2:10;

P=10.^(P_dB/10);
T=5;

tx_number=3;
rx_number=3;

chan_number=400000;

P_out1=[];
P_out2=[];
P_out3=[];
P_out4=[];
P_out5=[];
for P_count=1:length(P)
     out_number1=0;
     out_number2=0;
     out_number3=0;
     out_number4=0;
     out_number5=0;
    for Chan_count=1:chan_number
        h1=randn+sqrt(-1)*randn;
        h2=randn(tx_number,1)+sqrt(-1)*randn(tx_number,1);
        H3=randn(tx_number,rx_number)+sqrt(-1)*randn(tx_number,rx_number);
        h4=max(abs(h2).^2);
        h5=sum(abs(h2))^2/tx_number;
        SNR1=P(P_count)*abs(h1)^2;
        SNR2=P(P_count)*norm(h2)^2;
        M=H3'*H3;
        [U,V]=eig(M);
        v=U(:,rx_number);
        % eig_value=v'*M*v;
        h_bar=H3*v;
        SNR3=P(P_count)*norm(h_bar)^2;
        
        SNR4=P(P_count)*h4;
        SNR5=P(P_count)*h5;
        
        if (SNR1<T)
            out_number1=out_number1+1;
        end
        if (SNR2<T)
            out_number2=out_number2+1;
        end
        if (SNR3<T)
            out_number3=out_number3+1;
        end
        if (SNR4<T)
            out_number4=out_number4+1;
        end
         if (SNR5<T)
            out_number5=out_number5+1;
        end       
    end
    P_out1=[P_out1,out_number1/chan_number];
    P_out2=[P_out2,out_number2/chan_number];
    P_out3=[P_out3,out_number3/chan_number];
    P_out4=[P_out4,out_number4/chan_number];
    P_out5=[P_out5,out_number5/chan_number];
end

semilogy(P_dB,P_out1)
grid on
hold on

semilogy(P_dB,P_out2)
grid on
hold on

semilogy(P_dB,P_out3)
grid on
hold on

semilogy(P_dB,P_out4)
grid on
hold on

semilogy(P_dB,P_out5)
grid on
hold on