package com.saldi.domain.features.bitcoin.usecases

import com.saldi.domain.common.constants.DomainConstants.CHART_NAME_TRADE_VOLUME_PATH
import com.saldi.domain.common.constants.DomainConstants.ROLLING_AVERAGE_TRADE_VOLUME
import com.saldi.domain.common.constants.DomainConstants.TIME_SPAN_TRADE_VOLUME
import com.saldi.domain.common.state.DomainResult
import com.saldi.domain.common.usecase.FlowUseCase
import com.saldi.domain.features.bitcoin.di.IoDispatcher
import com.saldi.domain.features.bitcoin.entities.BitcoinValue
import com.saldi.domain.features.bitcoin.repositories.BitcoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Sourabh on 10/5/21
 */
class RetrieveBitcoinTradeVolumeUseCase @Inject constructor(
    private val bitcoinRepository: BitcoinRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, BitcoinValue>(ioDispatcher) {

    /**
     * Function that returns an [Flow] that will emit updates of the Bitcoin market price
     * information.
     *
     * @return Returns an [Flow].
     */
    override fun execute(parameters: Unit): Flow<DomainResult<BitcoinValue>> {
        return bitcoinRepository.fetchBitcoinInformation(
            CHART_NAME_TRADE_VOLUME_PATH,
            TIME_SPAN_TRADE_VOLUME,
            ROLLING_AVERAGE_TRADE_VOLUME
        )
    }
}